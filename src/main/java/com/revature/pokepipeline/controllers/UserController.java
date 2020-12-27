package com.revature.pokepipeline.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokepipeline.models.UserDTO;
import com.revature.pokepipeline.services.UserService;
import com.revature.pokepipeline.services.impl.UserServiceImpl;
import com.revature.pokepipeline.servlets.filters.CorsFilter;

public class UserController {
	
	private Logger log = LogManager.getLogger(CorsFilter.class);
	private ObjectMapper objectMapper = new ObjectMapper();
	private UserService userService = new UserServiceImpl();

	public void updateProfile(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader reader = req.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		String body = new String(stringBuilder);
		UserDTO userDTO = objectMapper.readValue(body, UserDTO.class);
		HttpSession httpSession = req.getSession(false);
		if (httpSession != null) {
			if (userService.updateProfile(userDTO)) {
				log.info("Successfully updated user profile.");
				res.setStatus(200);
			}
			else {
				res.setStatus(401);
				log.warn("Could not update user profile.");
			}
		}
		else {
			log.warn("Could not locate user.");
		}
	}

}
