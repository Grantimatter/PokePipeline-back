package com.revature.pokepipeline.daos;

import java.util.List;

import com.revature.pokepipeline.models.Pokemon;

public interface PokemonDAO {
	
	public void insertPokemon(Pokemon pokemon);
	public void updatePokemon(Pokemon pokemon);
	public Pokemon getPokemonById(int pokemonId);
	public List<Pokemon> getAllPokemon();

}
