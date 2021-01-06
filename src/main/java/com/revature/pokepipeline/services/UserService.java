package com.revature.pokepipeline.services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.revature.pokepipeline.models.Users;

public interface UserService {

	boolean updateProfile(Users user);

	boolean register(Users user);

	boolean login(Users user);

	Users getUserByUsername(String username);

	boolean updatePassword(String unencryptedPassword, Users user)
			throws NoSuchAlgorithmException, InvalidKeySpecException;

}
