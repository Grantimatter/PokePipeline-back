package com.revature.pokepipeline.models;

public class UserDTO {
	
	public int userId;
	public String username;
	public String password;
	public String email;
	public String description;
	public byte[] profilePicture;
	
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDTO(int userId, String username, String password, String email, String description,
			byte[] profilePicture) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.description = description;
		this.profilePicture = profilePicture;
	}
	public UserDTO(String username, String password, String email, String description, byte[] profilePicture) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.description = description;
		this.profilePicture = profilePicture;
	}
	

}
