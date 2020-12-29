package com.revature.pokepipeline.services;

import com.revature.pokepipeline.models.Users;

public interface UserService {

	boolean updateProfile(Users user);
	boolean register(Users user);
	boolean login(Users user);
	Users getUserByUsername(String username);

}
