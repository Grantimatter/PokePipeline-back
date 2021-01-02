package com.revature.pokepipeline.services;

import java.util.List;

import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.models.Trainer;

public interface PokemonService {

	void addPokemon(Pokemon pokemon);
	void updatePokemon(Pokemon pokemon);
	Pokemon getPokemonById(int id);
	void deletePokemon(Pokemon pokemon);
	
}
