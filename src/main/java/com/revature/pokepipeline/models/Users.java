package com.revature.pokepipeline.models;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity(name = "users")
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@Column(unique = true)
	private String username;
	private String password;
	@Column(unique = true)
	private String email;
	private String description;
	private byte[] profilePicture;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "user")
	private List<Pokemon> pokemonList;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "user")
	private List<Item> itemList;

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(int userId, String username, String email, String description, byte[] profilePicture,
			List<Pokemon> pokemonList, List<Item> itemList) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.description = description;
		this.profilePicture = profilePicture;
		this.pokemonList = pokemonList;
		this.itemList = itemList;
	}

	public Users(String username, String email, String description, byte[] profilePicture, List<Pokemon> pokemonList,
			List<Item> itemList) {
		super();
		this.username = username;
		this.email = email;
		this.description = description;
		this.profilePicture = profilePicture;
		this.pokemonList = pokemonList;
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", username=" + username + ", email=" + email + ", description="
				+ description + ", profilePicture=" + Arrays.toString(profilePicture) + ", pokemonList=" + pokemonList
				+ ", itemList=" + itemList + "]";
	}

	public Users(int userId, String username, String password, String email, String description, byte[] profilePicture,
			List<Pokemon> pokemonList, List<Item> itemList) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.description = description;
		this.profilePicture = profilePicture;
		this.pokemonList = pokemonList;
		this.itemList = itemList;
	}

	public Users(String username, String password, String email, String description, byte[] profilePicture,
			List<Pokemon> pokemonList, List<Item> itemList) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.description = description;
		this.profilePicture = profilePicture;
		this.pokemonList = pokemonList;
		this.itemList = itemList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((itemList == null) ? 0 : itemList.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((pokemonList == null) ? 0 : pokemonList.hashCode());
		result = prime * result + Arrays.hashCode(profilePicture);
		result = prime * result + userId;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (itemList == null) {
			if (other.itemList != null)
				return false;
		} else if (!itemList.equals(other.itemList))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (pokemonList == null) {
			if (other.pokemonList != null)
				return false;
		} else if (!pokemonList.equals(other.pokemonList))
			return false;
		if (!Arrays.equals(profilePicture, other.profilePicture))
			return false;
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public List<Pokemon> getPokemonList() {
		return pokemonList;
	}

	public void setPokemonList(List<Pokemon> pokemonList) {
		this.pokemonList = pokemonList;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

}
