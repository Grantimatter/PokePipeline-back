package com.revature.pokepipeline.services.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.models.Users;
import com.revature.pokepipeline.services.PokemonService;

class PokemonServiceImplTest {
	
	private static PokemonService pokemonService;
	private static Pokemon pokemon;
	private static Users user;

	@BeforeAll
	public static void setUp() {
		pokemonService = new PokemonServiceImpl();
		pokemon = new Pokemon();
		user = new Users();
	}
	
	@BeforeEach
	public void beforeEach() {
		pokemon.setPokemonId(1);
		pokemon.setPokemonAPI(1);
		pokemon.setCurrentHP(10);
		pokemon.setExperience(1);
		pokemon.setMove1API(1);
		pokemon.setMove2API(2);
		pokemon.setMove3API(3);
		pokemon.setMove4API(4);
		pokemon.setUser(null);
	}
	
	@Test
	public void testAddPokemonNull() {
		boolean isAdded = pokemonService.addPokemon(null);
		assertFalse(isAdded);
	}
	
	@Test
	public void testAddPokemonBadMove() {
		pokemon.setMove1API(0);
		boolean isAdded = pokemonService.addPokemon(pokemon);
		assertFalse(isAdded);
	}
	
	@Test
	public void testAddPokemonBadExperience() {
		pokemon.setExperience(0);
		boolean isAdded = pokemonService.addPokemon(pokemon);
		assertFalse(isAdded);
	}
	
	@Test
	public void testAddPokemonBadHp() {
		pokemon.setCurrentHP(0);
		boolean isAdded = pokemonService.addPokemon(pokemon);
		assertFalse(isAdded);
	}
	
	@Test
	public void testAddPokemonBadUser() {
		boolean isAdded = pokemonService.addPokemon(pokemon);
		assertFalse(isAdded);
	}
	
	@Test
	public void testUpdatePokemonNull() {
		boolean isUpdated = pokemonService.updatePokemon(null);
		assertFalse(isUpdated);
	}
	
	@Test
	public void testUpdatePokemonBadId() {
		pokemon.setPokemonId(0);
		boolean isUpdated = pokemonService.updatePokemon(pokemon);
		assertFalse(isUpdated);
	}
	
	@Test
	public void testUpdatePokemonBadMove() {
		pokemon.setMove1API(0);
		boolean isUpdated = pokemonService.updatePokemon(pokemon);
		assertFalse(isUpdated);
	}
	
	@Test
	public void testUpdatePokemonBadExperience() {
		pokemon.setExperience(0);
		boolean isUpdated = pokemonService.updatePokemon(pokemon);
		assertFalse(isUpdated);
	}
	
	@Test
	public void testUpdatePokemonBadUser() {
		boolean isUpdated = pokemonService.updatePokemon(pokemon);
		assertFalse(isUpdated);
	}
	
	@Test
	public void testDeletePokemonNull() {
		boolean isDeleted = pokemonService.deletePokemon(null);
		assertFalse(isDeleted);
	}
	
	@Test
	public void testDeletePokemonBadId() {
		pokemon.setPokemonId(0);
		boolean isDeleted = pokemonService.deletePokemon(pokemon);
		assertFalse(isDeleted);
	}
	
	@Test
	public void testDeletePokemonBadMove() {
		pokemon.setMove1API(0);
		boolean isDeleted = pokemonService.deletePokemon(pokemon);
		assertFalse(isDeleted);
	}
	
	@Test
	public void testDeletePokemonBadExperience() {
		pokemon.setExperience(0);
		boolean isDeleted = pokemonService.deletePokemon(pokemon);
		assertFalse(isDeleted);
	}
	
	@Test
	public void testDeletePokemonBadHp() {
		pokemon.setCurrentHP(1);
		boolean isDeleted = pokemonService.deletePokemon(pokemon);
		assertFalse(isDeleted);
	}
	
	@Test
	public void testDeletePokemonBadUser() {
		pokemon.setCurrentHP(0);
		boolean isDeleted = pokemonService.deletePokemon(pokemon);
		assertFalse(isDeleted);
	}
	
	@Test
	public void testGetPartyByUserNull() {
		List<Pokemon> pokemonList = pokemonService.getPartyByUser(null);
		assertEquals(0,pokemonList.size());
	}
	
	@Test
	public void testGetPartyByUserBadId() {
		user.setUserId(0);
		List<Pokemon> pokemonList = pokemonService.getPartyByUser(user);
		assertEquals(0,pokemonList.size());
	}
	
	@Test
	public void testGetPartyByUserBadUser() {
		user.setUserId(999);
		List<Pokemon> pokemonList = pokemonService.getPartyByUser(user);
		assertNull(pokemonList);
	}
	
}
