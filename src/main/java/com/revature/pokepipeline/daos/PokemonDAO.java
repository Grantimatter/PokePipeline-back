package com.revature.pokepipeline.daos;

import java.util.List;

import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.models.Users;

public interface PokemonDAO {
	
	boolean insertPokemon(Pokemon pokemon);
	boolean updatePokemon(Pokemon pokemon);
	boolean deletePokemon(Pokemon pokemon);
	List<Pokemon> getPartyByUser(Users user);

}
