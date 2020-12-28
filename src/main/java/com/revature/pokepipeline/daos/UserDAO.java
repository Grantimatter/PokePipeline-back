package com.revature.pokepipeline.daos;

import java.util.List;

import com.revature.pokepipeline.models.Users;

public interface UserDAO {
	
	public boolean insertUser(Users user);
	public boolean updateUser(Users user);
	public Users getUserById(int userId);
	public List<Users> getAllUsers();
	public Users getUserByUsername(String username);

}
