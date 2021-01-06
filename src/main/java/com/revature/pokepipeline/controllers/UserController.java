package com.revature.pokepipeline.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokepipeline.models.Users;
import com.revature.pokepipeline.services.UserService;
import com.revature.pokepipeline.services.impl.UserServiceImpl;

public class UserController {

	private Logger log = LogManager.getLogger(UserController.class);
	private ObjectMapper objectMapper = new ObjectMapper();
	private UserService userService = new UserServiceImpl();

	public void register(HttpServletRequest req, HttpServletResponse res) throws IOException {
		BufferedReader reader = req.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		String body = new String(stringBuilder);
		Users user = objectMapper.readValue(body, Users.class);

		if (userService.register(user)) {
			log.info("Successfully registered.");
			res.setStatus(200);
		} else {
			res.setStatus(400);
			log.warn("Could not register.");
		}

	}

	public void getProfile(HttpServletRequest req, HttpServletResponse res) throws IOException {

		HttpSession usersSession = req.getSession(false);
		String username = null;

		Users userProfile = null;
		String userProfileString = null;

		if (usersSession != null) {
			username = (String) usersSession.getAttribute("username");

			if (username != null || !username.equals("")) {
				userProfile = this.userService.getUserByUsername(username);
				userProfileString = this.objectMapper.writeValueAsString(userProfile);

				res.setStatus(200);
				res.addHeader("content-type", "application/json");
				res.getWriter().write(userProfileString);
			} else {
				res.setStatus(500);
			}

		} else {
			res.setStatus(401);
		}

	}

	public void updateProfile(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession userSession = req.getSession(false);
		String username = (String) userSession.getAttribute("username");
		boolean userProfileUpdateSuccess = false;
		Users userToUpdate = objectMapper.readValue(IOUtils.toString(req.getReader()), Users.class);
		Users user = this.userService.getUserByUsername(username);

		user.setEmail(userToUpdate.getEmail());
		user.setDescription(userToUpdate.getDescription());
		user.setProfilePicture(userToUpdate.getProfilePicture());
		userProfileUpdateSuccess = this.userService.updateProfile(user);

		if (userProfileUpdateSuccess)
			res.setStatus(200);
		else
			res.setStatus(400);

	}

	public void updatePassword(HttpServletRequest req, HttpServletResponse res) {
		HttpSession usersSession = req.getSession();
		String username = null;
		String newPassword = null;
		Users userToUpdate = null;
		Users passwordToken;
		boolean updateSuccess = false;

		try {
			if (usersSession != null) {
				username = (String) usersSession.getAttribute("username");
				userToUpdate = this.userService.getUserByUsername(username);
				passwordToken = objectMapper.readValue(req.getReader(), Users.class);
				newPassword = passwordToken.getPassword();
				updateSuccess = this.userService.updatePassword(newPassword, userToUpdate);

				if (updateSuccess)
					res.setStatus(200);
				else
					res.setStatus(400);

			} else {
				res.setStatus(401);
			}
		} catch (Exception e) {
			res.setStatus(500);
		} finally {
		}

	}

}
