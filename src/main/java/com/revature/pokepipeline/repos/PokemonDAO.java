package com.revature.pokepipeline.repos;

import java.util.List;

import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.models.Trainer;

public interface PokemonDAO {
	
	Pokemon insertPokemon(Pokemon pokemon);
	Pokemon updatePokemon(Pokemon pokemon);
	void deletePokemon(Pokemon pokemon);

	Pokemon getPokemonById(int id);
	Pokemon getPokemon(Pokemon pokemon);

}
