package com.revature.pokepipeline.services;

import com.revature.pokepipeline.models.Trainer;

public interface TrainerService {

	Trainer register(Trainer trainer);
	Trainer updateProfile(Trainer trainer, Trainer sessionTrainer);
	Trainer login(Trainer trainer, Trainer sessionTrainer);
	Trainer getTrainerByTrainerName(String username);

}
