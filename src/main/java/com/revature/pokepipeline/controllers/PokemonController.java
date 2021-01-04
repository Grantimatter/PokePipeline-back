package com.revature.pokepipeline.controllers;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokepipeline.models.dto.PokemonDTO;
import com.revature.pokepipeline.services.TrainerService;
import com.revature.pokepipeline.utility.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.models.Trainer;
import com.revature.pokepipeline.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/pokemon")
@CrossOrigin
public class PokemonController {

    private final Logger log = LogManager.getLogger(PokemonController.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PokemonService pokemonService;
    private final TrainerService trainerService;

    @Autowired
    public PokemonController(PokemonService pokemonService, TrainerService trainerService) {
        this.pokemonService = pokemonService;
        this.trainerService = trainerService;
    }

    @PostMapping
    public ResponseEntity<List<Pokemon>> addPokemon(@RequestBody PokemonDTO pokemonDTO, HttpServletRequest req) throws IOException {
        log.debug("Attempting to add a new pokemon with data: " + pokemonDTO);
        Pokemon pokemon = objectMapper.convertValue(pokemonDTO, Pokemon.class);
        if (pokemon != null) {
            Trainer trainer = SessionUtil.getTrainerFromSession(req);
            if (trainer != null) {
                pokemon.setTrainer(trainer);
                List<Pokemon> pokemonList = pokemonService.addPokemon(pokemon);
                trainerService.getTrainerByTrainerNameOrEmail(trainer.getTrainerName(), trainer.getEmail()).getPokemonList();
                if (pokemonList != null && pokemonList.size() > 0) {
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
    public ResponseEntity<Pokemon> updatePokemon(@RequestBody PokemonDTO pokemonDTO, HttpServletRequest req) throws IOException {
        Pokemon pokemon = objectMapper.convertValue(pokemonDTO, Pokemon.class);
        Pokemon dbPokemon = pokemonService.getPokemonById(pokemon.getPokemonId());
        Trainer trainer = SessionUtil.getTrainerFromSession(req);
        if (pokemon != null) {
            if (trainer != null) {
                if (isOwnedByTrainer(trainer, dbPokemon)) {
                    pokemon = pokemonService.updatePokemon(pokemon);
                    log.info("Successfully updated Pokemon.");
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(pokemon);
                } else {
                    log.warn(String.format("Pokemon with ID: %d does not belong to %s!", pokemon.getPokemonId(), trainer.getTrainerName()));
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
            }
            log.warn("Could not locate trainer.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        log.warn("Pokemon was an invalid format");
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @DeleteMapping
    public ResponseEntity<List<Pokemon>> deletePokemon(@RequestBody PokemonDTO pokemonDTO, HttpServletRequest req) throws IOException {
        Pokemon pokemon = pokemonService.getPokemonById(objectMapper.convertValue(pokemonDTO, Pokemon.class).getPokemonId());
        Trainer trainer = SessionUtil.getTrainerFromSession(req);
        if (pokemon != null) {
            if (trainer != null) {
                if (isOwnedByTrainer(trainer, pokemon)) {
                    List<Pokemon> pokemonList = pokemonService.deletePokemon(pokemon);
                    log.info("Successfully deleted Pokemon.");
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(pokemonList);
                } else {
                    log.warn(String.format("Pokemon with ID: %d does not belong to %s!", pokemon.getPokemonId(), trainer.getTrainerName()));
                }
            }
            log.warn("Could not locate user.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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
