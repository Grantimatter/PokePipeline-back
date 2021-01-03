package com.revature.pokepipeline.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.revature.pokepipeline.utility.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.pokepipeline.models.Trainer;
import com.revature.pokepipeline.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@ResponseBody
@RequestMapping(value = "/trainer")
@CrossOrigin
public class TrainerController {

    private Logger log = LogManager.getLogger(TrainerController.class);
    private TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PutMapping
    public ResponseEntity<Trainer> updateProfile(@RequestBody Trainer trainer, HttpServletRequest req) throws IOException {
        HttpSession httpSession = req.getSession(false);
        Trainer sessionTrainer = SessionUtil.getTrainerFromSession(req);
        if (sessionTrainer != null) {
            log.debug(String.format("Attempting to update %s with new data %s", sessionTrainer.getTrainerName(), trainer));
            trainerService.updateProfile(trainer, sessionTrainer);
            trainer = trainerService.getTrainerByTrainerName(trainer.getTrainerName());
            if(trainer != null){
                log.info("Successfully updated trainer profile.");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(trainer);
            }
            log.warn("Could not update trainer profile.");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<Trainer> register(@RequestBody Trainer trainer, HttpServletRequest req) throws IOException {
        log.debug("Entered Post");
        /*
        HttpSession httpSession = req.getSession(false);
        Trainer sessionTrainer = SessionUtil.getTrainerFromSession(req);
        if (httpSession != null && sessionTrainer == null) {
        log.debug("Attempting to register trainer: " + trainer);
            trainerService.register(trainer);
            trainer = trainerService.getTrainerByTrainerName(trainer.getTrainerName());
            if(trainer != null){
                log.info("Successfully registered.");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(trainer);
            }else{
                log.error("Error creating user");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
        }else if(sessionTrainer != null){
            log.warn("User already logged in");
            return ResponseEntity.status(HttpStatus.OK).build();
        }
*/
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
