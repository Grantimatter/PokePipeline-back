package com.revature.pokepipeline.services.impl;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.pokepipeline.repos.TrainerDAO;
import com.revature.pokepipeline.models.Trainer;
import com.revature.pokepipeline.services.TrainerService;
import com.revature.pokepipeline.utility.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService {

    private Logger log = LogManager.getLogger(TrainerServiceImpl.class);
    private TrainerDAO trainerDAO;

    @Autowired
    public TrainerServiceImpl(TrainerDAO trainerDAO) {
        this.trainerDAO = trainerDAO;
    }

    private boolean isValidTrainer(Trainer trainer) {
        if (trainer == null) {
            log.warn("Invalid trainer.");
            return false;
        } else if (trainer.getTrainerId() <= 0) {
            log.warn("Must have id to correctly update database.");
            return false;
        } else if (trainer.getTrainerName() == null || trainer.getTrainerName().equals("")) {
            log.warn("Invalid trainername.");
            return false;
        } else if (trainer.getPassword() == null || trainer.getPassword().equals("")) {
            log.warn("Invalid password.");
            return false;
        } else if (trainer.getEmail() == null || trainer.getEmail().equals("")) {
            log.warn("Invalid email.");
            return false;
        }
        return true;
    }

    @Override
    public Trainer updateProfile(Trainer trainer, Trainer sessionTrainer) {
        if (isValidTrainer(trainer) && trainer.getTrainerId() == sessionTrainer.getTrainerId()) {
            Encoder encoder = null;
            try {
                encoder = new Encoder();
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                log.error(e);
            }
            String encryptedPassword = encoder.encrypt(trainer.getPassword());
            trainer.setPassword(encryptedPassword);
            trainerDAO.updateTrainer(trainer);
            trainer = trainerDAO.getTrainerByTrainerName(trainer.getTrainerName());
            return trainer;
        }
        return null;
    }

    @Override
    public Trainer register(Trainer trainer) {
        if (trainer == null) {
            log.warn("Invalid trainer.");
        } else if (trainer.getTrainerName() == null || trainer.getTrainerName().equals("")) {
            log.warn("Invalid trainername.");
        } else if (trainer.getPassword() == null || trainer.getPassword().equals("")) {
            log.warn("Invalid password.");
        } else if (trainer.getEmail() == null || trainer.getEmail().equals("")) {
            log.warn("Invalid email.");
        } else {
            Encoder encoder = null;
            try {
                encoder = new Encoder();
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                log.error(e);
            }
            String encryptedPassword = encoder.encrypt(trainer.getPassword());
            trainer.setPassword(encryptedPassword);
            trainerDAO.insertTrainer(trainer);
            trainer = trainerDAO.getTrainerByTrainerName(trainer.getTrainerName());
            return trainer;
        }
        return null;
    }

    @Override
    public Trainer login(Trainer trainer, Trainer sessionTrainer) {
        if(isValidTrainer(trainer) && trainer.getTrainerId() == sessionTrainer.getTrainerId()) {
            Trainer dbTrainer = trainerDAO.getTrainerByTrainerName(trainer.getTrainerName());

            if (dbTrainer == null) {
                log.warn("Could not locate trainer.");
            } else {
                Encoder encoder = null;
                try {
                    encoder = new Encoder();
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                    log.error(e);
                }
                String decryptedPassword = null;
                try {
                    decryptedPassword = Encoder.decrypt(dbTrainer.getPassword(), encoder.getKey());
                } catch (GeneralSecurityException | IOException e) {
                    log.error(e);
                }
                if (decryptedPassword.equals(trainer.getPassword())) {
                    dbTrainer.setPassword(null);
                    return dbTrainer;
                } else {
                    log.warn("Password does not match.");
                }
            }
        }
        return null;
    }

    @Override
    public Trainer getTrainerByTrainerName(String trainerName) {
        Trainer trainer = null;
        if (trainerName == null || trainerName.equals("")) {
            log.warn("Invalid trainerName.");
        } else {
            trainer = trainerDAO.getTrainerByTrainerName(trainerName);
        }
        if (trainer == null) {
            log.warn("Could not locate trainer.");
        }
        return trainer;
    }

}
