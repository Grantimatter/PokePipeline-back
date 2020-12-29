package com.revature.pokepipeline.services;

import java.util.List;

import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.models.Users;

public interface PokemonService {

	boolean addPokemon(Pokemon pokemon);
	boolean updatePokemon(Pokemon pokemon);
	boolean deletePokemon(Pokemon pokemon);
	List<Pokemon> getPartyByUser(Users user);
	
}
