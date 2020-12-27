package com.revature.pokepipeline.services.impl;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.pokepipeline.daos.UserDAO;
import com.revature.pokepipeline.daos.impl.UserDAOImpl;
import com.revature.pokepipeline.models.Users;
import com.revature.pokepipeline.services.UserService;
import com.revature.pokepipeline.servlets.filters.CorsFilter;
import com.revature.pokepipeline.utility.Encoder;

public class UserServiceImpl implements UserService {
	
	private Logger log = LogManager.getLogger(CorsFilter.class);
	private UserDAO userDAO = new UserDAOImpl();

	@Override
	public boolean updateProfile(Users user) throws UnsupportedEncodingException, GeneralSecurityException {
		boolean isUpdated = false;
		if (user == null) {
			log.warn("Invalid user.");
		}
		else if (user.getUserId() <= 0) {
			log.warn("Must have id to correctly update database.");
		}
		else if (user.getUsername() == null || user.getUsername() == "") {
			log.warn("Invalid username.");
		}
		else if (user.getPassword() == null || user.getPassword() == "") {
			log.warn("Invalid password.");
		}
		else if (user.getEmail() == null || user.getEmail() == "") {
			log.warn("Invalid email.");
		}
		else {
			Encoder encoder = new Encoder();
			String encryptedPassword = Encoder.encrypt(user.getPassword(), encoder.getKey());
			user.setPassword(encryptedPassword);
			isUpdated = userDAO.updateUser(user);
		}
		return isUpdated;
	}

	@Override
	public boolean register(Users user) throws UnsupportedEncodingException, GeneralSecurityException {
		boolean isRegistered = false;
		if (user == null) {
			log.warn("Invalid user.");
		}
		else if (user.getUsername() == null || user.getUsername() == "") {
			log.warn("Invalid username.");
		}
		else if (user.getPassword() == null || user.getPassword() == "") {
			log.warn("Invalid password.");
		}
		else if (user.getEmail() == null || user.getEmail() == "") {
			log.warn("Invalid email.");
		}
		else {
			Encoder encoder = new Encoder();
			String encryptedPassword = Encoder.encrypt(user.getPassword(), encoder.getKey());
			user.setPassword(encryptedPassword);
			isRegistered = userDAO.insertUser(user);
		}
		return isRegistered;
	}

}
