package com.revature.pokepipeline.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokepipeline.models.dto.TrainerDTO;
import com.revature.pokepipeline.services.TrainerService;
import com.revature.pokepipeline.utility.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.pokepipeline.models.Trainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping(value = "/trainer")
public class TrainerController {

    private final Logger log = LogManager.getLogger(TrainerController.class);
    private final TrainerService trainerService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PutMapping
    public ResponseEntity<Trainer> updateProfile(@RequestBody TrainerDTO trainerDTO, HttpServletRequest req) {
        Trainer sessionTrainer = SessionUtil.getTrainerFromSession(req);
        Trainer trainer = objectMapper.convertValue(trainerDTO, Trainer.class);
        if (sessionTrainer != null) {
            String message = String.format("Attempting to update %s with new data %s", sessionTrainer.getTrainerName(), trainer);
            log.debug(message);
            trainerService.updateProfile(trainer, sessionTrainer);
            trainer = trainerService.getTrainerByTrainerNameOrEmail(trainer.getTrainerName(), trainer.getEmail());
            if(trainer != null){
                log.info("Successfully updated trainer profile.");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(trainer);
            }
            log.warn("Could not update trainer profile.");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<Trainer> register(@RequestBody TrainerDTO trainerDTO, HttpServletRequest req) throws IOException {
        Trainer trainer = objectMapper.convertValue(trainerDTO, Trainer.class);
        if(trainer != null){
            String message = String.format("Trainer DTO received: %s", trainer);
            log.info(message);
        }else{
            log.debug("No Trainer DTO Detected");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        Trainer sessionTrainer = SessionUtil.getTrainerFromSession(req);
        if (sessionTrainer == null) {
            trainerService.register(trainer);
            trainer = trainerService.getTrainerByTrainerNameOrEmail(trainer.getTrainerName(), trainer.getEmail());
            if(trainer != null){
                log.info("Successfully registered.");
                SessionUtil.setupLoginSession(req, trainer);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(trainer);
            }else{
                log.error("Error creating user");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
        }else if(sessionTrainer != null){
            log.warn("User already logged in");
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
