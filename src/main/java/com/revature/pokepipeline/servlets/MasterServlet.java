package com.revature.pokepipeline.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pokepipeline.controllers.AuthController;
import com.revature.pokepipeline.controllers.PokemonController;
import com.revature.pokepipeline.controllers.UserController;

@SuppressWarnings("serial")
public class MasterServlet extends HttpServlet {

	private final AuthController authController = new AuthController();
	private final PokemonController pokemonController = new PokemonController();
	private final UserController userController = new UserController();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		res.setStatus(404);

		final String URI = req.getRequestURI().replace("/PokePipeline/", "");

		// user cases
		switch (URI) {
			case "login":
				authController.login(req, res);
				break;

			case "logout":
				authController.logout(req, res);
				break;

			case "register":
				userController.register(req, res);
				break;

			case "updateprofile":
				userController.updateProfile(req, res);
				break;

			// pokemon cases
			case "addpokemon":
				pokemonController.addPokemon(req, res);
				break;

			case "updatepokemon":
				pokemonController.updatePokemon(req, res);
				break;

			case "getparty":
				pokemonController.getPartyByUser(req, res);
				break;

			case "deletepokemon": // this probably shouldn't be accessible to the user
				pokemonController.deletePokemon(req, res);
				break;
			case "user/getprofile":
				userController.getProfile(req, res);
				break;
			case "user/updatepassword":
				userController.updatePassword(req, res);
				break;
			case "user/updateprofile":
				userController.updateProfile(req, res);
				break;
			default:
				break;

		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
