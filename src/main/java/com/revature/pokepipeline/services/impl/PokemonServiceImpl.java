package com.revature.pokepipeline.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.pokepipeline.daos.PokemonDAO;
import com.revature.pokepipeline.daos.impl.PokemonDAOImpl;
import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.models.Users;
import com.revature.pokepipeline.services.PokemonService;

public class PokemonServiceImpl implements PokemonService {

	private Logger log = LogManager.getLogger(PokemonServiceImpl.class);
	private PokemonDAO pokemonDAO = new PokemonDAOImpl();

	@Override
	public boolean addPokemon(Pokemon pokemon) {
		boolean isAdded = false;
		if (pokemon == null) {
			log.warn("Invaid pokemon.");
		} else if (pokemon.getMove1API() <= 0 || pokemon.getMove2API() <= 0 || pokemon.getMove3API() <= 0
				|| pokemon.getMove4API() <= 0) {
			log.warn("Invalid move.");
		} else if (pokemon.getExperience() <= 0) {
			log.warn("Invalid experience.");
		} else if (pokemon.getCurrentHP() <= 0) {
			log.warn("Invalid hp amount.");
		} else if (pokemon.getUser() == null) {
			log.warn("Invalid user.");
		} else {
			isAdded = pokemonDAO.insertPokemon(pokemon);
		}
		if (!isAdded) {
			log.warn("Could not add Pokemon.");
		}
		return isAdded;
	}

	@Override
	public boolean updatePokemon(Pokemon pokemon) {
		boolean isUpdated = false;
		if (pokemon == null) {
			log.warn("Invaid pokemon.");
		} else if (pokemon.getPokemonId() <= 0) {
			log.warn("Must have id to correctly update database.");
		} else if (pokemon.getMove1API() <= 0 || pokemon.getMove2API() <= 0 || pokemon.getMove3API() <= 0
				|| pokemon.getMove4API() <= 0) {
			log.warn("Invalid move.");
		} else if (pokemon.getExperience() <= 0) {
			log.warn("Invalid experience.");
		} else if (pokemon.getUser() == null) {
			log.warn("Invalid user.");
		} else {
			isUpdated = pokemonDAO.updatePokemon(pokemon);
		}
		if (!isUpdated) {
			log.warn("Could not update Pokemon.");
		}
		return isUpdated;
	}

	@Override
	public boolean deletePokemon(Pokemon pokemon) {
		boolean isDeleted = false;
		if (pokemon == null) {
			log.warn("Invaid pokemon.");
		} else if (pokemon.getPokemonId() <= 0) {
			log.warn("Must have id to correctly update database.");
		} else if (pokemon.getMove1API() <= 0 || pokemon.getMove2API() <= 0 || pokemon.getMove3API() <= 0
				|| pokemon.getMove4API() <= 0) {
			log.warn("Invalid move.");
		} else if (pokemon.getExperience() <= 0) {
			log.warn("Invalid experience.");
		} else if (pokemon.getCurrentHP() > 0) {
			log.warn("Invalid hp amount. (Pokemon is not dead)");
		} else if (pokemon.getUser() == null) {
			log.warn("Invalid user.");
		} else {
			isDeleted = pokemonDAO.deletePokemon(pokemon);
		}
		if (!isDeleted) {
			log.warn("Could not delete Pokemon.");
		}
		return isDeleted;
	}

	@Override
	public List<Pokemon> getPartyByUser(Users user) {
		List<Pokemon> pokemonList = new ArrayList<>();
		if (user == null) {
			log.warn("Invalid user.");
		} else if (user.getUserId() <= 0) {
			log.warn("Invalid user id.");
		} else {
			pokemonList = pokemonDAO.getPartyByUser(user);
		}
		if (pokemonList == null) {
			log.warn("Could not get party of Pokemon.");
		}
		return pokemonList;
	}

}
