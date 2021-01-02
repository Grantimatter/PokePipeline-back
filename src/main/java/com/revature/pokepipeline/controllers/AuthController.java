package com.revature.pokepipeline.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pokepipeline.utility.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokepipeline.models.Trainer;
import com.revature.pokepipeline.services.TrainerService;
import com.revature.pokepipeline.services.impl.TrainerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin
public class AuthController {
	
	private Logger log = LogManager.getLogger(AuthController.class);
	private TrainerService trainerService;

	@Autowired
	public AuthController(TrainerService trainerService) {
		this.trainerService = trainerService;
	}

	@PostMapping
	public ResponseEntity<Trainer> login(HttpServletRequest req, @RequestBody Trainer trainer) throws IOException {
		Trainer sessionTrainer = SessionUtil.getTrainerFromSession(req);
		if (trainerService.login(trainer, sessionTrainer) != null && sessionTrainer == null) {
			trainer = trainerService.getTrainerByTrainerName(trainer.getTrainerName());
			if(trainer != null && SessionUtil.setupLoginSession(req, trainer)){
				return ResponseEntity.status(HttpStatus.FOUND).body(trainer);
			}
		} else {
			HttpSession httpSession = req.getSession(false);
			if (httpSession != null) {
				httpSession.invalidate();
			}
			log.warn("Login failed.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping
	public ResponseEntity<String> logout(HttpServletRequest req) {
		req.getSession(false).invalidate();
		return ResponseEntity.status(HttpStatus.OK).body("logout");
	}

}
