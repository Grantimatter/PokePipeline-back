package com.revature.pokepipeline;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import com.revature.pokepipeline.repos.TrainerDAO;
import com.revature.pokepipeline.services.PokemonService;
import com.revature.pokepipeline.services.TrainerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Driver {
	
	private static PokemonService pokemonService;
	private static TrainerService trainerService;
	private static TrainerDAO trainerDAO;

	public static void main(String[] args) throws UnsupportedEncodingException, GeneralSecurityException {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		TrainerDAO trainerDAO = applicationContext.getBean(TrainerDAO.class);



		// in case we need to run anything as a java application

//		Trainer topheryun = new Trainer(1, "topheryun", "pass", "topheryun@gmail.com", "Pokemon Master!", null, null, null);
		//topheryun = userService.getUserByUsername("topheryun");
//		userService.register(topheryun);
//		userService.updateProfile(topheryun);

		//Pokemon pokemon = new Pokemon(1, 19, 1, 1, 2, 3, 4, topheryun);
//		System.out.println(pokemonService.deletePokemon(pokemon));
//		System.out.println(userDAO.getUserByUsername("topheryun"));
//		pokemonService.addPokemon(pokemon);
//		System.out.println(pokemonService.getPartyByUser(topheryun));
		
	}

}
