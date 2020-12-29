package com.revature.pokepipeline.daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.pokepipeline.daos.PokemonDAO;
import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.models.Users;
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
		}
		return isUpdated;
	}
	
	@Override
	public boolean deletePokemon(Pokemon pokemon) {
		boolean isDeleted = false;
		Session session = HibernateUtility.getSession();
		Transaction transaction;
		try {
			transaction = session.beginTransaction();
			session.delete(pokemon);
			transaction.commit();
			isDeleted = true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return isDeleted;
	}

	@Override
	public List<Pokemon> getPartyByUser(Users user) {
		List<Pokemon> pokemonList = new ArrayList<>();
		Session session = HibernateUtility.getSession();
		Transaction transaction;
		try {
			transaction = session.beginTransaction();
			String HQL = "from Pokemon where userid=?1";
			Query<Pokemon> query = session.createQuery(HQL, Pokemon.class);
			query.setParameter(1, user.getUserId());
			pokemonList = query.list();
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e);
		}
		if (pokemonList.size() == 0) return null;
		else return pokemonList;
	}

}
