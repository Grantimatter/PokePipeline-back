package com.revature.pokepipeline.services;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.revature.pokepipeline.models.Users;

public interface UserService {

	boolean updateProfile(Users user) throws UnsupportedEncodingException, GeneralSecurityException;
	boolean register(Users user) throws UnsupportedEncodingException, GeneralSecurityException;

}
