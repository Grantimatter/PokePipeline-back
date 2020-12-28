package com.revature.pokepipeline.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokepipeline.models.Users;
import com.revature.pokepipeline.services.UserService;
import com.revature.pokepipeline.services.impl.UserServiceImpl;
import com.revature.pokepipeline.servlets.filters.CorsFilter;

public class AuthController {
	
	private Logger log = LogManager.getLogger(CorsFilter.class);
	private ObjectMapper objectMapper = new ObjectMapper();
	private UserService userService = new UserServiceImpl();

	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader reader = req.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		String body = new String(stringBuilder);
		Users user = objectMapper.readValue(body, Users.class);
		if (userService.login(user)) {
			HttpSession httpSession = req.getSession();
			httpSession.setAttribute("user", user);
			res.setStatus(200);
			log.info(user.getUsername() + " has logged in.");
		} else {
			HttpSession httpSession = req.getSession(false);
			if (httpSession != null) {
				httpSession.invalidate();
			}
			res.setStatus(401);
			log.warn("Login failed.");
		}
	}

	public void logout(HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		
		if (req.getMethod().equals("PUT")) {
			
			req.getSession().invalidate();
			
			res.setStatus(200);
		}
	}

}
