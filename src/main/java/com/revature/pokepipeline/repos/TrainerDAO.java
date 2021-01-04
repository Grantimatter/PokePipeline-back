package com.revature.pokepipeline.repos;

import java.util.List;

import com.revature.pokepipeline.models.Trainer;

public interface TrainerDAO {
	
	void insertTrainer(Trainer trainer);
	Trainer updateTrainer(Trainer trainer);
	void deleteTrainer(Trainer trainer);
	Trainer getTrainerById(int trainerId); // untested
	List<Trainer> getAllTrainers(); // untested
	Trainer getTrainerByTrainerNameOrEmail(String username, String email);

}
