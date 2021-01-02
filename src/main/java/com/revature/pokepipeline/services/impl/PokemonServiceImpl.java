package com.revature.pokepipeline.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.pokepipeline.repos.PokemonDAO;
import com.revature.pokepipeline.repos.impl.PokemonDAOImpl;
import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.models.Trainer;
import com.revature.pokepipeline.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PokemonServiceImpl implements PokemonService {

	private Logger log = LogManager.getLogger(PokemonServiceImpl.class);
	private PokemonDAO pokemonDAO;

	@Autowired
	public PokemonServiceImpl(PokemonDAO pokemonDAO){
		this.pokemonDAO = pokemonDAO;
	}

	public boolean isValidPokemon(Pokemon pokemon){
		if (pokemon == null) {
			log.warn("Invaid pokemon.");
			return false;
		} else if (pokemon.getMove1API() <= 0 || pokemon.getMove2API() <= 0 || pokemon.getMove3API() <= 0
				|| pokemon.getMove4API() <= 0) {
			log.warn("Invalid move.");
			return false;
		} else if (pokemon.getExperience() <= 0) {
			log.warn("Invalid experience.");
			return false;
		} else if (pokemon.getCurrentHP() <= 0) {
			log.warn("Invalid hp amount.");
			return false;
		} else if (pokemon.getTrainer() == null) {
			log.warn("Invalid user.");
			return false;
		}
		return true;
	}

	@Override
	public void addPokemon(Pokemon pokemon) {
		if(isValidPokemon(pokemon)) pokemonDAO.insertPokemon(pokemon);
	}

	@Override
	public void updatePokemon(Pokemon pokemon) {
		if(isValidPokemon(pokemon)) pokemonDAO.updatePokemon(pokemon);
	}

	@Override
	public Pokemon getPokemonById(int id) {
		return pokemonDAO.getPokemonById(id);
	}

	@Override
	public void deletePokemon(Pokemon pokemon) {
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
		} else if (pokemon.getTrainer() == null) {
			log.warn("Invalid user.");
		} else {
			pokemonDAO.deletePokemon(pokemon);
		}
		if (!isDeleted) {
			log.warn("Could not delete Pokemon.");
		}
	}
}
