package com.revature.pokepipeline.services.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.pokepipeline.models.Users;
import com.revature.pokepipeline.services.UserService;

class UserServiceImplTest {

	private static UserService userService;
	private static Users user;
	
	@BeforeAll
	public static void setUp() {
		userService = new UserServiceImpl();
		user = new Users();
	}
	
	@BeforeEach
	public void beforeEach() {
		user.setUserId(1);
		user.setUsername("username");
		user.setPassword("password");
		user.setEmail("user@revature.net");
		user.setDescription("");
		user.setProfilePicture(null);
	}
	
	@Test
	public void testUpdateProfileNull() {
		boolean isUpdated = userService.updateProfile(null);
		assertFalse(isUpdated);
	}
	
	@Test
	public void testUpdateProfileBadId() {
		user.setUserId(0);
		boolean isUpdated = userService.updateProfile(user);
		assertFalse(isUpdated);
	}
	
	@Test
	public void testUpdateProfileBadUsername() {
		user.setUsername(null);
		boolean isUpdated = userService.updateProfile(user);
		assertFalse(isUpdated);
	}
	
	@Test
	public void testUpdateProfileBadPassword() {
		user.setPassword(null);
		boolean isUpdated = userService.updateProfile(user);
		assertFalse(isUpdated);
	}
	
	@Test
	public void testUpdateProfileBadEmail() {
		user.setEmail(null);
		boolean isUpdated = userService.updateProfile(user);
		assertFalse(isUpdated);
	}
	
	@Test
	public void testUpdateProfileNonExistant() {
		user.setUserId(999);
		boolean isUpdated = userService.updateProfile(user);
		assertFalse(isUpdated);
	}
	
	@Test
	public void testRegisterNull() {
		boolean isRegistered = userService.register(null);
		assertFalse(isRegistered);
	}
	
	@Test
	public void testRegisterBadUsername() {
		user.setUsername(null);
		boolean isRegistered = userService.register(user);
		assertFalse(isRegistered);
	}
	
	@Test
	public void testRegisterBadPassword() {
		user.setPassword(null);
		boolean isRegistered = userService.register(user);
		assertFalse(isRegistered);
	}
	
	@Test
	public void testRegisterBadEmail() {
		user.setEmail(null);
		boolean isRegistered = userService.register(user);
		assertFalse(isRegistered);
	}
	
	@Test
	public void testRegisterTestUser() {
		user.setUserId(1000);
		boolean isRegistered = userService.register(user);
		assertFalse(isRegistered);
	}
	
	@Test
	public void testLoginNull() {
		boolean isLoggedIn = userService.login(null);
		assertFalse(isLoggedIn);
	}
	
	@Test
	public void testLoginBadUsername() {
		user.setUsername(null);
		boolean isLoggedIn = userService.login(user);
		assertFalse(isLoggedIn);
	}
	
	@Test
	public void testLoginBadPassword() {
		user.setPassword(null);
		boolean isLoggedIn = userService.login(user);
		assertFalse(isLoggedIn);
	}
	
	@Test
	public void testLoginWrongPassword() {
		user.setUserId(900);
		boolean isLoggedIn = userService.login(user);
		assertFalse(isLoggedIn);
	}
	
	@Test
	public void testLogin() {
		user.setUserId(900);
		user.setPassword("pass");
		boolean isLoggedIn = userService.login(user);
		assertTrue(isLoggedIn);
	}
	
	@Test
	public void testGetUserByUsername() {
		Users user = userService.getUserByUsername("username");
		assertNotNull(user);
	}
	
	@Test
	public void testGetUserByUsernameNull() {
		Users user = userService.getUserByUsername(null);
		assertNull(user);
	}
	
	@Test
	public void testGetUserByUsernameInvalidUsername() {
		Users user = userService.getUserByUsername("thisguydoesntexist");
		assertNull(user);
	}

}
