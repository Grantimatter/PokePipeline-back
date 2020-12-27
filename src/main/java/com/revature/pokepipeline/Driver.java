package com.revature.pokepipeline;

import com.revature.pokepipeline.daos.ItemDAO;
import com.revature.pokepipeline.daos.PokemonDAO;
import com.revature.pokepipeline.daos.UserDAO;
import com.revature.pokepipeline.daos.impl.ItemDAOImpl;
import com.revature.pokepipeline.daos.impl.PokemonDAOImpl;
import com.revature.pokepipeline.daos.impl.UserDAOImpl;
import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.models.Users;
import com.revature.pokepipeline.services.PokemonService;
import com.revature.pokepipeline.services.impl.PokemonServiceImpl;

public class Driver {
	
	private static UserDAO userDAO = new UserDAOImpl();
	private static PokemonDAO pokemonDAO = new PokemonDAOImpl();
	private static ItemDAO itemDAO = new ItemDAOImpl();
	private static PokemonService pokemonService = new PokemonServiceImpl();

	public static void main(String[] args) {
		// in case we need to run anything as a java application

		
		Users topheryun = new Users(1, "test", "topheryun@gmail.com", "Pokemon Master", null, null, null);
//		userDAO.insertUser(topheryun);

		Pokemon pokemon = new Pokemon(1, 2, 49, 16, 1, 2, 3, 4, topheryun);
		pokemonService.updatePokemon(pokemon);
		
		
	}

}
