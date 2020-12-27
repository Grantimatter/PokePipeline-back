package com.revature.pokepipeline.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.pokepipeline.daos.UserDAO;
import com.revature.pokepipeline.daos.impl.UserDAOImpl;
import com.revature.pokepipeline.models.UserDTO;
import com.revature.pokepipeline.models.Users;
import com.revature.pokepipeline.services.UserService;
import com.revature.pokepipeline.servlets.filters.CorsFilter;

public class UserServiceImpl implements UserService {
	
	private Logger log = LogManager.getLogger(CorsFilter.class);
	private UserDAO userDAO = new UserDAOImpl();

	@Override
	public boolean updateProfile(UserDTO userDTO) {
		boolean isUpdated = false;
		if (userDTO == null) {
			log.warn("Invalid user.");
		}
		else if (userDTO.userId <= 0) {
			log.warn("Must have id to correctly update database.");
		}
		else if (userDTO.username == null || userDTO.username == "") {
			log.warn("Invalid username.");
		}
		else if (userDTO.password == null || userDTO.password == "") {
			log.warn("Invalid password.");
		}
		else if (userDTO.email == null || userDTO.password == "") {
			log.warn("Invalid email.");
		}
		else {
			Users user = new Users(
					userDTO.userId,
					userDTO.username,
					userDTO.email,
					userDTO.description,
					userDTO.profilePicture,
					null,
					null
					);
// need to update password here as well. is inside userDTO.password here
			isUpdated = userDAO.updateUser(user);
		}
		return isUpdated;
	}

}
