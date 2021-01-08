package com.revature.pokepipeline.controllers;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokepipeline.models.dto.PokemonDTO;
import com.revature.pokepipeline.utility.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.models.Trainer;
import com.revature.pokepipeline.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping(value = "/pokemon")
public class PokemonController {

    private final Logger log = LogManager.getLogger(PokemonController.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @PostMapping
    public ResponseEntity<List<Pokemon>> addPokemon(@RequestBody PokemonDTO pokemonDTO, HttpServletRequest req) {
        String message = String.format("Attempting to add a new pokemon with data:  %s", pokemonDTO);
        log.debug(message);
        Pokemon pokemon = objectMapper.convertValue(pokemonDTO, Pokemon.class);
        if (pokemon != null) {
            Trainer trainer = SessionUtil.getTrainerFromSession(req);
            if (trainer != null) {
                pokemon.setTrainer(trainer);
                List<Pokemon> pokemonList = pokemonService.addPokemon(pokemon);
                if (!pokemonList.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(pokemonList);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
                }
            }

            log.warn("Could not locate trainer.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        log.warn("Pokemon was an invalid format");
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PutMapping
    public ResponseEntity<Pokemon> updatePokemon(@RequestBody PokemonDTO pokemonDTO, HttpServletRequest req) {
        Pokemon pokemon = objectMapper.convertValue(pokemonDTO, Pokemon.class);
        Pokemon dbPokemon = pokemonService.getPokemonById(pokemon.getPokemonId());
        Trainer trainer = SessionUtil.getTrainerFromSession(req);
            if (trainer != null) {
                if (isOwnedByTrainer(trainer, dbPokemon)) {
                    pokemon = pokemonService.updatePokemon(pokemon);
                    log.info("Successfully updated Pokemon.");
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(pokemon);
                } else {
                    String message = String.format("Pokemon with ID: %d does not belong to %s!", pokemon.getPokemonId(), trainer.getTrainerName());
                    log.warn(message);
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
            }
            log.warn("Could not locate trainer.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Pokemon>> deletePokemon(@PathVariable("id") int id, HttpServletRequest req) {
        Pokemon pokemon = pokemonService.getPokemonById(id);
        Trainer trainer = SessionUtil.getTrainerFromSession(req);
        String message = String.format("%s retrieved from session. Trying to match to Pokemon: %s", trainer.getTrainerId(), pokemon.getTrainer().getTrainerId());
        log.debug(message);
        if (pokemon != null) {
            if (trainer != null) {
                if (isOwnedByTrainer(trainer, pokemon)) {
                    List<Pokemon> pokemonList = pokemonService.deletePokemon(pokemon);
                    log.info("Successfully deleted Pokemon.");
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(pokemonList);
                } else {
                    String message2 = String.format("Pokemon with ID: %d does not belong to %s!", pokemon.getPokemonId(), trainer.getTrainerName());
                    log.warn(message2);
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
            }
            log.warn("Could not locate user.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        log.warn("Pokemon was an invalid format");
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    private boolean isOwnedByTrainer(Trainer trainer, Pokemon pokemon) {
        for (Pokemon p : trainer.getPokemonList()) {
            if (p.getPokemonId() == pokemon.getPokemonId()) {
                return true;
            }
        }
        return false;
    }
}
