package com.revature.pokepipeline.services.impl;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.pokepipeline.repos.TrainerDAO;
import com.revature.pokepipeline.models.Trainer;
import com.revature.pokepipeline.services.TrainerService;
import com.revature.pokepipeline.utility.Encoder;

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
        }else if (trainer.getTrainerName() == null || trainer.getTrainerName().equals("")) {
            log.warn("Invalid trainername.");
            return false;
        } else if (trainer.getPassword() == null || trainer.getPassword().equals("")) {
            log.warn("Invalid password.");
            return false;
        }
        return true;
    }

    @Override
    public Trainer updateProfile(Trainer trainer, Trainer sessionTrainer) {
        if (isValidTrainer(trainer) && trainer.getTrainerId() <= 0 && trainer.getTrainerId() == sessionTrainer.getTrainerId()) {
            Encoder encoder = null;
            try {
                encoder = new Encoder();
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                log.error(e);
            }
            String encryptedPassword = encoder.encrypt(trainer.getPassword());
            trainer.setPassword(encryptedPassword);
            trainerDAO.updateTrainer(trainer);
            trainer = trainerDAO.getTrainerByTrainerNameOrEmail(trainer.getTrainerName(), trainer.getEmail());
            return trainer;
        }
        return null;
    }

    @Override
    public Trainer register(Trainer trainer) {
        if (isValidTrainer(trainer) && trainer.getEmail() != null && !trainer.getEmail().equals("")) {
            Trainer existingTrainer = trainerDAO.getTrainerByTrainerNameOrEmail(trainer.getTrainerName(), trainer.getEmail());
            if(existingTrainer!= null){
                log.warn("Trainer already exists!");
                return null;
            }
            Encoder encoder = null;
            try {
                encoder = new Encoder();
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                log.error(e);
            }
            String encryptedPassword = encoder.encrypt(trainer.getPassword());
            trainer.setPassword(encryptedPassword);
            trainerDAO.insertTrainer(trainer);
            trainer = trainerDAO.getTrainerByTrainerNameOrEmail(trainer.getTrainerName(), trainer.getEmail());
            return trainer;
        }
        return null;
    }

    @Override
    public Trainer login(Trainer trainer, Trainer sessionTrainer) {
        Trainer dbTrainer = trainerDAO.getTrainerByTrainerNameOrEmail(trainer.getTrainerName(), trainer.getEmail());
        if(isValidTrainer(trainer) && sessionTrainer == null) {

            if (dbTrainer == null) {
                log.warn("Could not locate trainer account.");
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
    public Trainer getTrainerByTrainerNameOrEmail(String trainerName, String email) {
        Trainer trainer = null;
        if (trainerName == null || trainerName.equals("")) {
            log.warn("Invalid trainerName.");
        } else {
            trainer = trainerDAO.getTrainerByTrainerNameOrEmail(trainerName, email);
        }
        if (trainer == null) {
            log.warn("Could not locate trainer.");
        }
        return trainer;
    }

}
