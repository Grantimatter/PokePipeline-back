package com.revature.pokepipeline.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokepipeline.models.dto.TrainerDTO;
import com.revature.pokepipeline.utility.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.pokepipeline.models.Trainer;
import com.revature.pokepipeline.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	private final Logger log = LogManager.getLogger(AuthController.class);
	private final TrainerService trainerService;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	public AuthController(TrainerService trainerService) {
		this.trainerService = trainerService;
	}

	/**
	 * Used for logging in and creating a session.
	 * @param trainerDTO The trainer object to create
	 * @param req The HttpServletRequest object sent from the client
	 * @return ResponseEntity<Trainer>
	 */
	@PostMapping
	public ResponseEntity<Trainer> login(@RequestBody TrainerDTO trainerDTO, HttpServletRequest req) {
		Trainer trainer = objectMapper.convertValue(trainerDTO, Trainer.class);
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
	public ResponseEntity<Trainer> checkSession(HttpServletRequest req) {
		Trainer sessionTrainer = SessionUtil.getTrainerFromSession(req);
		if(sessionTrainer != null){
			Trainer trainer = trainerService.getTrainerByTrainerNameOrEmail(sessionTrainer.getTrainerName(), sessionTrainer.getEmail());
			if(trainer != null) {
				log.debug("Returning valid trainer from session");
				trainer.setPassword(null);
				return  ResponseEntity.status(HttpStatus.OK).body(trainer);
			}
			log.warn("No trainer associated with current session");
		}
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
