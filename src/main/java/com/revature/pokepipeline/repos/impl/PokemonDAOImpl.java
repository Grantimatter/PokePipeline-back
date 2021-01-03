package com.revature.pokepipeline.repos.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.pokepipeline.repos.PokemonDAO;
import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.models.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository @Transactional
public class PokemonDAOImpl implements PokemonDAO {

	public SessionFactory sessionFactory;

	@Autowired
	public PokemonDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insertPokemon(Pokemon pokemon) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(pokemon);
	}

	@Override
	public Pokemon getPokemonById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Pokemon.class, id);
	}

	@Override
	public Pokemon getPokemon(Pokemon pokemon) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(Pokemon.class, pokemon);
	}

	@Override
	public void updatePokemon(Pokemon pokemon) {
		Session session = sessionFactory.getCurrentSession();
		session.update(pokemon);
	}
	
	@Override
	public void deletePokemon(Pokemon pokemon) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(pokemon);
	}
}
