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
		Pokemon pokemon = null;
		Session session = HibernateUtility.getSession();	
		Transaction transaction;
		try {
			transaction = session.beginTransaction();
			pokemon = session.get(Pokemon.class, pokemonId);
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return pokemon;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pokemon> getAllPokemon() {
		List<Pokemon> pokemonList = null;
		Session session = HibernateUtility.getSession();
		Transaction transaction;
		try {
			transaction = session.beginTransaction();
			pokemonList = session.createQuery("from Pokemon").list();
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return pokemonList;
	}

}
