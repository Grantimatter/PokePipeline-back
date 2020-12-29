package com.revature.pokepipeline;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import com.revature.pokepipeline.daos.UserDAO;
import com.revature.pokepipeline.daos.impl.UserDAOImpl;
import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.models.Users;
import com.revature.pokepipeline.services.PokemonService;
import com.revature.pokepipeline.services.UserService;
import com.revature.pokepipeline.services.impl.PokemonServiceImpl;
import com.revature.pokepipeline.services.impl.UserServiceImpl;

public class Driver {
	
	private static PokemonService pokemonService = new PokemonServiceImpl();
	private static UserService userService = new UserServiceImpl();
	private static UserDAO userDAO = new UserDAOImpl();

	public static void main(String[] args) throws UnsupportedEncodingException, GeneralSecurityException {
		// in case we need to run anything as a java application

		Users topheryun = new Users(1, "topheryun", "pass", "topheryun@gmail.com", "Pokemon Master!", null, null, null);
		topheryun = userService.getUserByUsername("topheryun");
//		userService.register(topheryun);
//		userService.updateProfile(topheryun);
		

		
		Pokemon pokemon = new Pokemon(1, 19, 1, 1, 2, 3, 4, topheryun);
//		System.out.println(pokemonService.deletePokemon(pokemon));
//		System.out.println(userDAO.getUserByUsername("topheryun"));
//		pokemonService.addPokemon(pokemon);
//		System.out.println(pokemonService.getPartyByUser(topheryun));
		
	}

}
