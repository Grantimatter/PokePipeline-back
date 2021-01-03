package com.revature.pokepipeline.services;

import java.util.List;

import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.models.Trainer;

public interface PokemonService {

	Pokemon addPokemon(Pokemon pokemon);
	Pokemon updatePokemon(Pokemon pokemon);
	Pokemon getPokemonById(int id);
	Pokemon getPokemon(Pokemon pokemon);
	void deletePokemon(Pokemon pokemon);
	
}
