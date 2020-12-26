package com.revature.pokepipeline.daos.impl;

import java.util.List;

import org.hibernate.Session;

import com.revature.pokepipeline.daos.PokemonDAO;
import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.utility.HibernateUtility;


public class PokemonDAOImpl implements PokemonDAO {

	@Override
	public void insertPokemon(Pokemon pokemon) {
		Session session = HibernateUtility.getSession();
		session.save(pokemon);
	}

	@Override
	public void updatePokemon(Pokemon pokemon) {
		Session session = HibernateUtility.getSession();
		session.merge(pokemon);
	}

	@Override
	public Pokemon getPokemonById(int pokemonId) {
		Session session = HibernateUtility.getSession();	
		return session.get(Pokemon.class, pokemonId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pokemon> getAllPokemon() {
		Session session = HibernateUtility.getSession();
		List<Pokemon> pokemonList = session.createQuery("from Pokemon").list();
		return pokemonList;
	}

}
