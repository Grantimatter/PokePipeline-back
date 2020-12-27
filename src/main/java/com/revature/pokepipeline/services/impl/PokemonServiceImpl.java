package com.revature.pokepipeline.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.pokepipeline.daos.PokemonDAO;
import com.revature.pokepipeline.daos.impl.PokemonDAOImpl;
import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.services.PokemonService;
import com.revature.pokepipeline.servlets.filters.CorsFilter;

public class PokemonServiceImpl implements PokemonService {
	
	private static Logger log = LogManager.getLogger(CorsFilter.class);
	private PokemonDAO pokemonDAO = new PokemonDAOImpl();

	@Override
	public boolean addPokemon(Pokemon pokemon) {
		boolean isAdded = false;
		if (pokemon.getMove1API() <= 0 || pokemon.getMove2API() <= 0
				|| pokemon.getMove3API() <= 0 || pokemon.getMove4API() <= 0) {
			log.warn("Invalid move.");
		}
		else if (pokemon.getExperience() <= 0) {
			log.warn("Invalid experience.");
		}
		else if (pokemon.getCurrentHP() <= 0) {
			log.warn("Invalid hp amount.");
		}
		else if (pokemon.getUser() == null) {
			log.warn("Invalid user.");
		}
		else {
			isAdded = pokemonDAO.insertPokemon(pokemon);
		}
		return isAdded;
	}

}
