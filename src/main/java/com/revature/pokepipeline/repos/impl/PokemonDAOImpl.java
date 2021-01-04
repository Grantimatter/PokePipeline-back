package com.revature.pokepipeline.repos.impl;

import com.revature.pokepipeline.repos.PokemonDAO;
import com.revature.pokepipeline.models.Pokemon;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
	public Pokemon insertPokemon(Pokemon pokemon) {
		Session session = sessionFactory.getCurrentSession();
		session.save(pokemon);
		return pokemon;
	}

	@Override
	public Pokemon getPokemonById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Pokemon.class, id);
	}

	@Override
	public Pokemon getPokemon(Pokemon pokemon) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Pokemon.class, pokemon.getPokemonId());
	}

	@Override
	public Pokemon updatePokemon(Pokemon pokemon) {
		Session session = sessionFactory.getCurrentSession();
		return (Pokemon) session.merge(pokemon);
	}
	
	@Override
	public void deletePokemon(Pokemon pokemon) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(pokemon);
	}
}
