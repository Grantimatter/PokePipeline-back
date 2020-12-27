package com.revature.pokepipeline.servlets;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.pokepipeline.controllers.AuthController;
import com.revature.pokepipeline.controllers.PokemonController;
import com.revature.pokepipeline.controllers.UserController;
import com.revature.pokepipeline.servlets.filters.CorsFilter;

public class MasterServlet extends HttpServlet {
	
	private Logger log = LogManager.getLogger(CorsFilter.class);
	private final AuthController authController = new AuthController();
	private final PokemonController pokemonController = new PokemonController();
	private final UserController userController = new UserController();
	
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
			
		case "register":
			try {
				userController.register(req, res);
			} catch (IOException | GeneralSecurityException e) {
				log.error(e);
			}
			break;
			
		case "updateprofile":
			try {
				userController.updateProfile(req, res);
			} catch (IOException | GeneralSecurityException e) {
				log.error(e);
			}
			break;
			
			// pokemon cases
		case "addpokemon":
			pokemonController.addPokemon(req, res);
			break;
			
		case "updatepokemon":
			pokemonController.updatePokemon(req, res);
			break;
			
		case "deletepokemon": // this probably shouldn't be accessible to the user
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
