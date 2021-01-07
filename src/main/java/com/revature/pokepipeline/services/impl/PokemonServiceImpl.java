package com.revature.pokepipeline.services.impl;

import com.revature.pokepipeline.repos.TrainerDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.pokepipeline.repos.PokemonDAO;
import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.services.PokemonService;

import java.util.List;

@Service
public class PokemonServiceImpl implements PokemonService {

	private Logger log = LogManager.getLogger(PokemonServiceImpl.class);
	private PokemonDAO pokemonDAO;
	private TrainerDAO trainerDAO;

	@Autowired
	public PokemonServiceImpl(PokemonDAO pokemonDAO, TrainerDAO trainerDAO) {
		this.pokemonDAO = pokemonDAO;
		this.trainerDAO = trainerDAO;
	}

	public boolean isValidPokemon(Pokemon pokemon) {
		if (pokemon == null) {
			log.warn("Invalid pokemon.");
			return false;
		} else if (pokemon.getMove1API() <= 0 || pokemon.getMove2API() <= 0 || pokemon.getMove3API() <= 0
				|| pokemon.getMove4API() <= 0) {
			log.warn("Invalid move.");
			return false;
		} else if (pokemon.getExperience() < 0) {
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
	public List<Pokemon> addPokemon(Pokemon pokemon) {
		if (isValidPokemon(pokemon)) {
			pokemon = pokemonDAO.insertPokemon(pokemon);
			return trainerDAO.getTrainerById(pokemon.getTrainer().getTrainerId()).getPokemonList();
		}
		return null;
	}

	@Override
	public Pokemon updatePokemon(Pokemon pokemon) {
		Pokemon dbPokemon = pokemonDAO.getPokemon(pokemon);
		if(pokemon.getCurrentHP() > 0) dbPokemon.setCurrentHP(pokemon.getCurrentHP());
		if(pokemon.getExperience() > 0) dbPokemon.setExperience(pokemon.getExperience());
		if(pokemon.getMove1API() > 0) dbPokemon.setMove1API(pokemon.getMove1API());
		if(pokemon.getMove2API() > 0) dbPokemon.setMove1API(pokemon.getMove2API());
		if(pokemon.getMove3API() > 0) dbPokemon.setMove1API(pokemon.getMove3API());
		if(pokemon.getMove4API() > 0) dbPokemon.setMove1API(pokemon.getMove4API());
		return pokemonDAO.updatePokemon(dbPokemon);
	}

	@Override
	public Pokemon getPokemonById(int id) {
		return pokemonDAO.getPokemonById(id);
	}

	@Override
	public Pokemon getPokemon(Pokemon pokemon) {
		return pokemonDAO.getPokemon(pokemon);
	}

	@Override
	public List<Pokemon> deletePokemon(Pokemon pokemon) {
		if (!isValidPokemon(pokemon)) {
			return null;
		} else if (pokemon.getPokemonId() <= 0) {
			log.warn("Must have valid id to correctly update database.");
			return null;
		} else {
			pokemonDAO.deletePokemon(pokemon);
			log.info("Pokemon deleted.");
		}
		return trainerDAO.getTrainerById(pokemon.getTrainer().getTrainerId()).getPokemonList();
	}
}
