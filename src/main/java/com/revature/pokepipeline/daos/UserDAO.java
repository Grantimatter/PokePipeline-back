package com.revature.pokepipeline.daos;

import java.util.List;

import com.revature.pokepipeline.models.Users;

public interface UserDAO {
	
	public void insertUser(Users user);
	public void updateUser(Users user);
	public Users getUserById(int userId);
	public List<Users> getAllUsers();
	// find by username

}
