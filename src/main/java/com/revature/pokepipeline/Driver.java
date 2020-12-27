package com.revature.pokepipeline;

import java.util.ArrayList;
import java.util.List;

import com.revature.pokepipeline.daos.ItemDAO;
import com.revature.pokepipeline.daos.PokemonDAO;
import com.revature.pokepipeline.daos.UserDAO;
import com.revature.pokepipeline.daos.impl.ItemDAOImpl;
import com.revature.pokepipeline.daos.impl.PokemonDAOImpl;
import com.revature.pokepipeline.daos.impl.UserDAOImpl;
import com.revature.pokepipeline.models.Item;
import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.models.Users;

public class Driver {
	
	private static UserDAO userDAO = new UserDAOImpl();
	private static PokemonDAO pokemonDAO = new PokemonDAOImpl();
	private static ItemDAO itemDAO = new ItemDAOImpl();

	public static void main(String[] args) {
		// in case we need to run anything as a java application
		
//		Users user1 = new Users("test", null, null);
		
		Pokemon pokemon1 = new Pokemon(0, 20, 5, 1, 2, 3, 4, null);
		Pokemon pokemon2 = new Pokemon(0, 24, 5, 5, 6, 7, 8, null);
		List<Pokemon> pokemonList = new ArrayList<>();
		pokemonList.add(pokemon1);
		pokemonList.add(pokemon2);
		
		Item item1 = new Item(1, null);
		Item item2 = new Item(2, null);
		List<Item> itemsList = new ArrayList<>();
		itemsList.add(item1);
		itemsList.add(item2);
		
		Users user1 = new Users(1, "test", pokemonList, null);
		
		userDAO.updateUser(user1);
		
		
//		pokemonDAO.insertPokemon(pokemon1);
		
		
//		userDAO.insertUser(user1);
//		pokemonDAO.insertPokemon(pokemon1);
//		pokemonDAO.insertPokemon(pokemon2);
//		
//		itemDAO.insertItem(item1);
//		itemDAO.insertItem(item2);
		
		
		
//		userDAO.insertUser(user1);
		
//		System.out.println(userDAO.getUserById(1));
		
		
		
	}

}
