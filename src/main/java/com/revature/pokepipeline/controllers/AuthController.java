package com.revature.pokepipeline.controllers;

import java.io.IOException;

import com.revature.pokepipeline.utility.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.pokepipeline.models.Trainer;
import com.revature.pokepipeline.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	private Logger log = LogManager.getLogger(AuthController.class);
	private TrainerService trainerService;

	@Autowired
	public AuthController(TrainerService trainerService) {
		this.trainerService = trainerService;
	}

	@PostMapping
	public ResponseEntity<Trainer> login(@RequestBody Trainer trainer, HttpServletRequest req) throws IOException {
		Trainer sessionTrainer = SessionUtil.getTrainerFromSession(req);
		if (trainerService.login(trainer, sessionTrainer) != null && sessionTrainer == null) {
			trainer = trainerService.getTrainerByTrainerNameOrEmail(trainer.getTrainerName(), trainer.getEmail());
			if(trainer != null && SessionUtil.setupLoginSession(req, trainer)) {
				return ResponseEntity.status(HttpStatus.OK).body(trainer);
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

	@GetMapping
	public ResponseEntity<Trainer> checkSession(HttpServletRequest req) throws IOException {
		Trainer sessionTrainer = SessionUtil.getTrainerFromSession(req);
		Trainer trainer = trainerService.getTrainerByTrainerNameOrEmail(sessionTrainer.getTrainerName(), sessionTrainer.getEmail());
		if(trainer != null) {
			log.debug("Returning valid trainer from session");
			return  ResponseEntity.status(HttpStatus.OK).body(trainer);
		}
		log.warn("No trainer associated with current session");
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

	@PutMapping
	public ResponseEntity<String> logout(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if(session != null){
			session.invalidate();
			return ResponseEntity.status(HttpStatus.OK).body("logout");
		}
		log.warn("User tried logging out, but was not logged in");
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

}
