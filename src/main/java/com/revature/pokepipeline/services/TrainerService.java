package com.revature.pokepipeline.services;

import com.revature.pokepipeline.models.Trainer;

public interface TrainerService {

	void register(Trainer trainer);
	void updateProfile(Trainer trainer, Trainer sessionTrainer);
	Trainer login(Trainer trainer, Trainer sessionTrainer);
	Trainer getTrainerByTrainerName(String username);

}
