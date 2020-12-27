package com.revature.pokepipeline.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pokepipeline.controllers.AuthController;
import com.revature.pokepipeline.controllers.PokemonController;
import com.revature.pokepipeline.controllers.UserController;

public class MasterServlet extends HttpServlet {
	
	private final AuthController authController = new AuthController();
	private final PokemonController pokemonController = new PokemonController();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		res.setContentType("application/json");
		res.setStatus(404);

		final String URI = req.getRequestURI().replace("/pokepipeline/", "");
		
		// user cases
		switch (URI) {
		case "login":
			authController.login(req, res);
			break;
			
		case "logout":
			authController.logout(req, res);
			break;
			
		case "updateprofile":
			UserController.updateProfile(req, res);
			break;
			
			// pokemon cases
		case "addpokemon": // for inserting pokemon to db (not updating)
			pokemonController.addPokemon(req, res);
			break;
			
		case "updatepokemon": // for updating hp/experience
			pokemonController.updatePokemon(req, res);
			break;
			
		case "deletepokemon": // this id probably not needed. maybe just use to clean up database
			pokemonController.deletePokemon(req, res);
			break;
			
		default:
			break;
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doGet(req, res);
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doGet(req, res);
	}

}
