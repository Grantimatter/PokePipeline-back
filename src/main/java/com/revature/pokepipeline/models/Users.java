package com.revature.pokepipeline.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(unique=true)
	private String username;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Pokemon> pokemonList;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Item> itemList;
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(int userId, String username, List<Pokemon> pokemonList, List<Item> itemList) {
		super();
		this.userId = userId;
		this.username = username;
		this.pokemonList = pokemonList;
		this.itemList = itemList;
	}
	public Users(String username, List<Pokemon> pokemonList, List<Item> itemList) {
		super();
		this.username = username;
		this.pokemonList = pokemonList;
		this.itemList = itemList;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", pokemonList=" + pokemonList + ", itemList="
				+ itemList + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemList == null) ? 0 : itemList.hashCode());
		result = prime * result + ((pokemonList == null) ? 0 : pokemonList.hashCode());
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
		if (itemList == null) {
			if (other.itemList != null)
				return false;
		} else if (!itemList.equals(other.itemList))
			return false;
		if (pokemonList == null) {
			if (other.pokemonList != null)
				return false;
		} else if (!pokemonList.equals(other.pokemonList))
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
