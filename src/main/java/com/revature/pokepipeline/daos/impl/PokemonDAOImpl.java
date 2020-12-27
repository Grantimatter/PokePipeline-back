package com.revature.pokepipeline.daos.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.pokepipeline.daos.PokemonDAO;
import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.utility.HibernateUtility;


public class PokemonDAOImpl implements PokemonDAO {

	@Override
	public boolean insertPokemon(Pokemon pokemon) {
		boolean isAdded = false;
		Session session = HibernateUtility.getSession();
		Transaction transaction;
		try {
			transaction = session.beginTransaction();
			session.save(pokemon);
			transaction.commit();
			isAdded = true;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return isAdded;
	}

	@Override
	public boolean updatePokemon(Pokemon pokemon) {
		boolean isUpdated = false;
		Session session = HibernateUtility.getSession();
		Transaction transaction;
		try {
			transaction = session.beginTransaction();
			session.merge(pokemon);
			transaction.commit();
			isUpdated = true;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return isUpdated;
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
