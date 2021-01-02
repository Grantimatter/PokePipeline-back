package com.revature.pokepipeline.repos;

import java.util.List;

import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.models.Trainer;

public interface PokemonDAO {
	
	void insertPokemon(Pokemon pokemon);
	void updatePokemon(Pokemon pokemon);
	void deletePokemon(Pokemon pokemon);

	Pokemon getPokemonById(int id);

}
