package com.revature.pokepipeline.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokepipeline.models.Pokemon;
import com.revature.pokepipeline.models.Users;
import com.revature.pokepipeline.services.PokemonService;
import com.revature.pokepipeline.services.impl.PokemonServiceImpl;
import com.revature.pokepipeline.servlets.filters.CorsFilter;

public class PokemonController {
	
	private static Logger log = LogManager.getLogger(CorsFilter.class);
	private ObjectMapper objectMapper = new ObjectMapper();
	private PokemonService pokemonService = new PokemonServiceImpl();

	public void addPokemon(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader reader = req.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		String body = new String(stringBuilder);
		Pokemon pokemon = objectMapper.readValue(body, Pokemon.class);
		HttpSession httpSession = req.getSession(false);
		if (httpSession != null) {
			Users user = (Users) httpSession.getAttribute("user");
			pokemon.setUser(user);
			if (pokemonService.addPokemon(pokemon)) {
				log.info("Successfully added Pokemon to your party.");
				res.setStatus(200);
			}
			else {
				res.setStatus(401);
				log.warn("Could not add Pokemon to your party.");
			}
		}
		else {
			log.warn("Warning: Could not locate user.");
		}
	}

	public void updatePokemon(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader reader = req.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		String body = new String(stringBuilder);
		Pokemon pokemon = objectMapper.readValue(body, Pokemon.class);
		HttpSession httpSession = req.getSession(false);
		if (httpSession != null) {
			Users user = (Users) httpSession.getAttribute("user");
			pokemon.setUser(user);
			if (pokemonService.updatePokemon(pokemon)) {
				res.setStatus(200);
				log.info("Successfully updated Pokemon.");
			}
			else {
				res.setStatus(401);
				log.warn("Could not update Pokemon.");
			}
		}
		else {
			log.warn("Warning: Could not locate user.");
		}
	}

}
