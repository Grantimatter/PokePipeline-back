package com.revature.pokepipeline.daos;

import java.util.List;

import com.revature.pokepipeline.models.Pokemon;

public interface PokemonDAO {
	
	public boolean insertPokemon(Pokemon pokemon);
	public boolean updatePokemon(Pokemon pokemon);
	public boolean deletePokemon(Pokemon pokemon);
	public Pokemon getPokemonById(int pokemonId);
	public List<Pokemon> getAllPokemon();

}
