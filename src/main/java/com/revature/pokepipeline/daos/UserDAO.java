package com.revature.pokepipeline.daos;

import java.util.List;

import com.revature.pokepipeline.models.Users;

public interface UserDAO {
	
	boolean insertUser(Users user);
	boolean updateUser(Users user);
	Users getUserById(int userId); // untested
	List<Users> getAllUsers(); // untested
	Users getUserByUsername(String username);

}
