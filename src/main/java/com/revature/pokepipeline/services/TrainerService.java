package com.revature.pokepipeline.services;

import com.revature.pokepipeline.models.Trainer;

public interface TrainerService {

	Trainer register(Trainer trainer);
	/**
	 * @param trainer The Trainer received from the HTTP request
	 */
	Trainer updateProfile(Trainer trainer, Trainer sessionTrainer);
	Trainer login(Trainer trainer, Trainer sessionTrainer);
	Trainer getTrainerByTrainerNameOrEmail(String username, String email);
	void deleteTrainer(Trainer trainer);

}
