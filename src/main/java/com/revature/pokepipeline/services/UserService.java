package com.revature.pokepipeline.services;

import com.revature.pokepipeline.models.Users;

public interface UserService {

	public boolean updateProfile(Users user);
	public boolean register(Users user);
	public boolean login(Users user);

}
