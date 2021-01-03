package com.revature.pokepipeline.services.impl;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.models.Trainer;
import com.revature.pokepipeline.services.PokemonService;

class PokemonServiceImplTest {

	@Autowired
	private static PokemonService pokemonService;
	
	@Test
	public void testIsValidPokemonNull() {
		List<Pokemon> pokemonList = pokemonService.addPokemon(null);
		assertNull(pokemonList);
	}
	
	@Test
	public void testIsValidPokemonBadMove() {
		Pokemon pokemon = new Pokemon();
		List<Pokemon> pokemonList = pokemonService.addPokemon(pokemon);
		assertNull(pokemonList);
	}
	
	@Test
	public void testIsValidPokemonBadExperience() {
		Pokemon pokemon = new Pokemon();
		pokemon.setMove1API(1);
		pokemon.setMove2API(2);
		pokemon.setMove3API(3);
		pokemon.setMove4API(4);
		pokemon.setExperience(-1);
		List<Pokemon> pokemonList = pokemonService.addPokemon(pokemon);
		assertNull(pokemonList);
	}

	@Test
	public void testIsValidPokemonBadCurrentHP() {
		Pokemon pokemon = new Pokemon();
		pokemon.setMove1API(1);
		pokemon.setMove2API(2);
		pokemon.setMove3API(3);
		pokemon.setMove4API(4);
		pokemon.setExperience(1);
		pokemon.setCurrentHP(0);
		List<Pokemon> pokemonList = pokemonService.addPokemon(pokemon);
		assertNull(pokemonList);
	}
	
	@Test
	public void testIsValidPokemonBadTrainer() {
		Pokemon pokemon = new Pokemon();
		pokemon.setMove1API(1);
		pokemon.setMove2API(2);
		pokemon.setMove3API(3);
		pokemon.setMove4API(4);
		pokemon.setExperience(1);
		pokemon.setCurrentHP(1);
		pokemon.setTrainer(null);
		List<Pokemon> pokemonList = pokemonService.addPokemon(pokemon);
		assertNull(pokemonList);
	}
	
	@Test
	public void testDeletePokemonBadId() {
		Trainer trainer = new Trainer();
		Pokemon pokemon = new Pokemon();
		pokemon.setPokemonId(-1);
		pokemon.setMove1API(1);
		pokemon.setMove2API(2);
		pokemon.setMove3API(3);
		pokemon.setMove4API(4);
		pokemon.setExperience(1);
		pokemon.setCurrentHP(1);
		pokemon.setTrainer(trainer);
		List<Pokemon> pokemonList = pokemonService.deletePokemon(pokemon);
		assertNull(pokemonList);
	}
	
}
