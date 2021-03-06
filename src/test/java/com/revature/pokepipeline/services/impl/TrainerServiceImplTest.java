package com.revature.pokepipeline.services.impl;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.pokepipeline.models.Trainer;
import com.revature.pokepipeline.services.TrainerService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@SpringJUnitWebConfig(locations = {"/applicationContext.xml"})
class TrainerServiceImplTest {

	private static final Logger log = LogManager.getLogger(PokemonServiceImplTest.class);

	@Autowired
	private TrainerService trainerService;

	@Test
	void testIsValidTrainerNull() {
		Trainer trainer = trainerService.register(null);
		assertNull(trainer);
	}
	
	@Test
	void testIsValidTrainerNullName() {
		Trainer trainer = new Trainer();
		trainer.setTrainerName(null);
		trainer = trainerService.register(null);
		assertNull(trainer);
	}
	
	@Test
	void testIsValidTrainerNullPassword() {
		Trainer trainer = new Trainer();
		trainer.setTrainerName("test");
		trainer.setPassword(null);
		trainer = trainerService.register(null);
		assertNull(trainer);
	}

	@Test
	void testTrainerInsert() {
		Trainer trainer = new Trainer();
		trainer.setTrainerName("testTrainer");
		trainer.setPassword("password78");
		trainer.setEmail("testTrainer@email.net");
		Trainer loginTrainer = trainer;
		trainerService.register(trainer);
		//assertNotNull(trainer);

		testTrainerUpdate(loginTrainer);
		testTrainerLogin(loginTrainer);
		trainerService.deleteTrainer(loginTrainer);
	}

	void testTrainerLogin(Trainer loginTrainer) {
		loginTrainer = trainerService.login(loginTrainer, null);
		assertNotNull(loginTrainer);
	}

	void testTrainerUpdate(Trainer updateTrainer){
		updateTrainer.setDescription("This is my description!");
		log.debug("Update Trainer: " + updateTrainer);
		updateTrainer = trainerService.updateProfile(updateTrainer, updateTrainer);
		log.debug(updateTrainer);
		assertNotNull(updateTrainer);
	}

	@Test
	void testGetTrainerByTrainerNameOrEmailFail() {
		Trainer trainer = new Trainer(900,"128i90asdjj210uasd","asd21sadcas2d","d21asdacwaewa","", null, null);
		trainer = trainerService.getTrainerByTrainerNameOrEmail(trainer.getTrainerName(), trainer.getEmail());
		assertNull(trainer);
	}

}
