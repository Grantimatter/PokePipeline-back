package com.revature.pokepipeline.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pokepipeline.services.TrainerService;
import com.revature.pokepipeline.utility.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.models.Trainer;
import com.revature.pokepipeline.services.PokemonService;
import com.revature.pokepipeline.services.impl.PokemonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pokemon")
@CrossOrigin
public class PokemonController {

    private Logger log = LogManager.getLogger(PokemonController.class);
    private PokemonService pokemonService;
    private TrainerService trainerService;

    @Autowired
    public PokemonController(PokemonService pokemonService, TrainerService trainerService) {
        this.pokemonService = pokemonService;
        this.trainerService = trainerService;
    }

    @PostMapping
    public ResponseEntity<List<Pokemon>> addPokemon(HttpServletRequest req, @RequestBody Pokemon pokemon) throws IOException {
        Trainer trainer = SessionUtil.getTrainerFromSession(req);
        if (trainer != null) {
            pokemon.setTrainer(trainer);
            pokemonService.addPokemon(pokemon);
            List<Pokemon> pokemonList = trainerService.getTrainerByTrainerName(trainer.getTrainerName()).getPokemonList();
            if(pokemonList != null){
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(pokemonList);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
        } else {
            log.warn("Could not locate trainer.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping
    public ResponseEntity<Pokemon> updatePokemon(HttpServletRequest req, @RequestBody Pokemon pokemon) throws IOException {
        Trainer trainer = SessionUtil.getTrainerFromSession(req);
        if (trainer != null) {
            if (isOwnedByTrainer(trainer, pokemon)) {
                pokemonService.updatePokemon(pokemon);
                pokemon = pokemonService.getPokemonById(pokemon.getPokemonId());
                log.info("Successfully updated Pokemon.");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(pokemon);
            } else {
                log.warn("Pokemon does not belong to this trainer!");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }

        log.warn("Could not locate trainer.");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    }

    @DeleteMapping
    public ResponseEntity<String> deletePokemon(HttpServletRequest req, @RequestBody Pokemon pokemon) throws IOException {
        Trainer trainer = SessionUtil.getTrainerFromSession(req);
        if (trainer != null) {
            if (isOwnedByTrainer(trainer, pokemon)) {
                pokemonService.deletePokemon(pokemon);
                log.info("Successfully deleted Pokemon.");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("pokemon deleted");
            } else {
                log.warn(String.format("Pokemon with ID: %d does not belong to %s!", pokemon.getPokemonId(), trainer.getTrainerName()));
            }
        } else {
            log.warn("Could not locate user.");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private boolean isOwnedByTrainer(Trainer trainer, Pokemon pokemon){
        for (Pokemon p : trainer.getPokemonList()) {
            if (p.getPokemonId() == pokemon.getPokemonId()) {
                return true;
            }
        }
        return false;
    }

}
