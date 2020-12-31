package com.revature.pokepipeline.services.impl;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.pokepipeline.daos.UserDAO;
import com.revature.pokepipeline.daos.impl.UserDAOImpl;
import com.revature.pokepipeline.models.Users;
import com.revature.pokepipeline.services.UserService;
import com.revature.pokepipeline.utility.Encoder;

public class UserServiceImpl implements UserService {

	private Logger log = LogManager.getLogger(UserServiceImpl.class);
	private UserDAO userDAO = new UserDAOImpl();

	@Override
	public boolean updateProfile(Users user) {
		boolean isUpdated = false;
		if (user == null) {
			log.warn("Invalid user.");
		} else if (user.getUserId() <= 0) {
			log.warn("Must have id to correctly update database.");
		} else if (user.getUsername() == null || user.getUsername().equals("")) {
			log.warn("Invalid username.");
		} else if (user.getPassword() == null || user.getPassword().equals("")) {
			log.warn("Invalid password.");
		} else if (user.getEmail() == null || user.getEmail().equals("")) {
			log.warn("Invalid email.");
		} else {
			Encoder encoder = null;
			try {
				encoder = new Encoder();
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				log.error(e);
			}
			String encryptedPassword = encoder.encrypt(user.getPassword());
			user.setPassword(encryptedPassword);
			isUpdated = userDAO.updateUser(user);
		}
		if (!isUpdated) {
			log.warn("Could not update profile.");
		}
		return isUpdated;
	}

	@Override
	public boolean register(Users user) {
		boolean isRegistered = false;
		if (user == null) {
			log.warn("Invalid user.");
		} else if (user.getUsername() == null || user.getUsername().equals("")) {
			log.warn("Invalid username.");
		} else if (user.getPassword() == null || user.getPassword().equals("")) {
			log.warn("Invalid password.");
		} else if (user.getEmail() == null || user.getEmail().equals("")) {
			log.warn("Invalid email.");
		} else {
			Encoder encoder = null;
			try {
				encoder = new Encoder();
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				log.error(e);
			}
			String encryptedPassword = encoder.encrypt(user.getPassword());
			user.setPassword(encryptedPassword);
			isRegistered = userDAO.insertUser(user);
		}
		if (!isRegistered) {
			log.warn("Could not register user.");
		}
		return isRegistered;
	}

	@Override
	public boolean login(Users user) {
		boolean isLoggedIn = false;
		if (user == null) {
			log.warn("Invalid user.");
		} else if (user.getUsername() == null || user.getUsername().equals("")) {
			log.warn("Invalid username.");
		} else if (user.getPassword() == null || user.getPassword().equals("")) {
			log.warn("Invalid password.");
		} else {
			Users dbUser = userDAO.getUserByUsername(user.getUsername());
			if (dbUser == null) {
				log.warn("Could not locate user.");
			} else {
				Encoder encoder = null;
				try {
					encoder = new Encoder();
				} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
					log.error(e);
				}
				String decryptedPassword = null;
				try {
					decryptedPassword = Encoder.decrypt(dbUser.getPassword(), encoder.getKey());
				} catch (GeneralSecurityException | IOException e) {
					log.error(e);
				}
				if (decryptedPassword.equals(user.getPassword())) {
					isLoggedIn = true;
				} else {
					log.warn("Password does not match.");
				}
			}
		}
		if (!isLoggedIn) {
			log.warn("Could not log in.");
		}
		return isLoggedIn;
	}

	@Override
	public Users getUserByUsername(String username) {
		Users user = null;
		if (username == null || username.equals("")) {
			log.warn("Invalid username.");
		} else {
			user = userDAO.getUserByUsername(username);
		}
		if (user == null) {
			log.warn("Could not locate user.");
		}
		return user;
	}

}
