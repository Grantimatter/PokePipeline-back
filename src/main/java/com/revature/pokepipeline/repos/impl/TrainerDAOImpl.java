package com.revature.pokepipeline.repos.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.pokepipeline.repos.TrainerDAO;
import com.revature.pokepipeline.models.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Repository @Transactional
public class TrainerDAOImpl implements TrainerDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public TrainerDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insertTrainer(Trainer trainer) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(trainer);
	}

	@Override
	public void updateTrainer(Trainer trainer) {
		Session session = sessionFactory.getCurrentSession();
		session.update(trainer);
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
	public Trainer getTrainerByTrainerName(String trainerName) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Trainer> criteriaQuery = criteriaBuilder.createQuery(Trainer.class);
		Root<Trainer> trainer = criteriaQuery.from(Trainer.class);
		Predicate trainerNamePredicate = criteriaBuilder.equal(trainer.get("trainerName"), trainerName);
		criteriaQuery.where(trainerNamePredicate);
		return session.createQuery(criteriaQuery).getSingleResult();
	}

}
