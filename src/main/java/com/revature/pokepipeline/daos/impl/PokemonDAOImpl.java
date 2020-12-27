package com.revature.pokepipeline.daos.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.pokepipeline.daos.PokemonDAO;
import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.utility.HibernateUtility;


public class PokemonDAOImpl implements PokemonDAO {

	@Override
	public void insertPokemon(Pokemon pokemon) {
		Session session = HibernateUtility.getSession();
		Transaction transaction;
		try {
			transaction = session.beginTransaction();
			session.save(pokemon);
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
	}

	@Override
	public void updatePokemon(Pokemon pokemon) {
		Session session = HibernateUtility.getSession();
		Transaction transaction;
		try {
			transaction = session.beginTransaction();
			session.merge(pokemon);
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
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
