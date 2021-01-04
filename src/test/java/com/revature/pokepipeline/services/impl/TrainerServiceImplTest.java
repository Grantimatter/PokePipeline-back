package com.revature.pokepipeline.services.impl;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.pokepipeline.models.Trainer;
import com.revature.pokepipeline.services.TrainerService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
class TrainerServiceImplTest {

	@Autowired
	private TrainerService trainerService;
	
	@Test
	public void testIsValidTrainerNull() {
		Trainer trainer = trainerService.register(null);
		assertNull(trainer);
	}
	
	@Test
	public void testIsValidTrainerNullName() {
		Trainer trainer = new Trainer();
		trainer.setTrainerName(null);
		trainer = trainerService.register(null);
		assertNull(trainer);
	}
	
	@Test
	public void testIsValidTrainerNullPassword() {
		Trainer trainer = new Trainer();
		trainer.setTrainerName("test");
		trainer.setPassword(null);
		trainer = trainerService.register(null);
		assertNull(trainer);
	}
	
	@Test
	public void testUpdateProfile() {
		Trainer trainer = new Trainer(900,"test","pass","test@email.net","", null, null);
		trainer = trainerService.updateProfile(trainer, trainer);
		assertNotNull(trainer);
	}
	
	@Test
	public void testRegister() {
		Trainer trainer = new Trainer(901,"newTestUser","pass","newTestUser@email.net","", null, null);
		trainer = trainerService.register(trainer);
		assertNotNull(trainer);
	}
	
	@Test
	public void testLogin() {
		Trainer trainer = new Trainer(900,"test","pass","test@email.net","", null, null);
		trainer = trainerService.login(trainer, trainer);
		assertNotNull(trainer);
	}
	
	@Test
	public void testGetTrainerByTrainerNameOrEmailFail() {
		Trainer trainer = new Trainer(900,"test","pass","test@email.net","", null, null);
		trainer = trainerService.register(trainer);
		assertNull(trainer);
	}

}
