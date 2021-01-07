package com.revature.pokepipeline.repos.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.revature.pokepipeline.repos.TrainerDAO;
import com.revature.pokepipeline.models.Trainer;

@Repository
@Transactional
public class TrainerDAOImpl implements TrainerDAO {

    private static final Logger log = LogManager.getLogger(TrainerDAOImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public TrainerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insertTrainer(Trainer trainer) {
        Session session = sessionFactory.getCurrentSession();
        session.save(trainer);
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) {
        Session session = sessionFactory.getCurrentSession();
        return (Trainer) session.merge(trainer);
    }

    @Override
    public Trainer getTrainerById(int trainerId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Trainer.class, trainerId);
    }

    @Override
    public List<Trainer> getAllTrainers() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaQuery<Trainer> criteriaQuery = session.getCriteriaBuilder().createQuery(Trainer.class);
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Trainer getTrainerByTrainerNameOrEmail(String trainerName, String email) {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Trainer> criteriaQuery = criteriaBuilder.createQuery(Trainer.class);
        Root<Trainer> trainer = criteriaQuery.from(Trainer.class);
        Predicate trainerNamePredicate = criteriaBuilder.equal(trainer.get("trainerName"), trainerName);
        Predicate trainerEmailPredicate = criteriaBuilder.equal(trainer.get("email"), email);
        Predicate nameOrEmail = criteriaBuilder.or(trainerNamePredicate, trainerEmailPredicate);
        criteriaQuery.where(nameOrEmail);

        try {
            Trainer retrievedTrainer = session.createQuery(criteriaQuery).getSingleResult();
            if (retrievedTrainer != null) {
                return retrievedTrainer;
            }
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }

        log.warn("No results found for the query");
        return null;
    }

    @Override
    public void deleteTrainer(Trainer trainer) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(trainer);
    }
}
