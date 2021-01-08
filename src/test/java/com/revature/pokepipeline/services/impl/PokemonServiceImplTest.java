package com.revature.pokepipeline.services.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.models.Trainer;
import com.revature.pokepipeline.services.PokemonService;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitWebConfig(locations = {"/applicationContext.xml"})
class PokemonServiceImplTest {

	private static final Logger log = LogManager.getLogger(PokemonServiceImplTest.class);

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private PokemonService pokemonService;

	@Test
	public void testIsValidPokemonNull() {
		List<Pokemon> pokemonList = pokemonService.addPokemon(null);
		assertNotNull(pokemonList);
	}

	@Test
	public void testIsValidPokemonBadMove() {
		Pokemon pokemon = new Pokemon();
		List<Pokemon> pokemonList = pokemonService.addPokemon(pokemon);
		assertNotNull(pokemonList);
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
		assertNotNull(pokemonList);
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
		assertNotNull(pokemonList);
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
		assertNotNull(pokemonList);
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
		assertNotNull(pokemonList);
	}

}
