package com.revature.pokepipeline.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pokemon {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pokemonId;
	
	private int pokemonAPI;
	private int currentHP;
	private int experience;
	private int move1API;
	private int move2API;
	private int move3API;
	private int move4API;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, targetEntity=Users.class)
	@JoinColumn(name="userId")
	private Users user;
	
	public Pokemon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pokemon(int pokemonId, int pokemonAPI, int currentHP, int experience, int move1api, int move2api, int move3api,
			int move4api, Users user) {
		super();
		this.pokemonId = pokemonId;
		this.pokemonAPI = pokemonAPI;
		this.currentHP = currentHP;
		this.experience = experience;
		move1API = move1api;
		move2API = move2api;
		move3API = move3api;
		move4API = move4api;
		this.user = user;
	}

	public Pokemon(int pokemonAPI, int currentHP, int experience, int move1api, int move2api, int move3api, int move4api,
			Users user) {
		super();
		this.pokemonAPI = pokemonAPI;
		this.currentHP = currentHP;
		this.experience = experience;
		move1API = move1api;
		move2API = move2api;
		move3API = move3api;
		move4API = move4api;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Pokemon [pokemonId=" + pokemonId + ", pokemonAPI=" + pokemonAPI + ", currentHP=" + currentHP + ", experience="
				+ experience + ", move1API=" + move1API + ", move2API=" + move2API + ", move3API=" + move3API + ", move4API="
				+ move4API + ", user=" + user.getUsername() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(currentHP);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + experience;
		result = prime * result + move1API;
		result = prime * result + move2API;
		result = prime * result + move3API;
		result = prime * result + move4API;
		result = prime * result + pokemonAPI;
		result = prime * result + pokemonId;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Pokemon other = (Pokemon) obj;
		if (Double.doubleToLongBits(currentHP) != Double.doubleToLongBits(other.currentHP))
			return false;
		if (experience != other.experience)
			return false;
		if (move1API != other.move1API)
			return false;
		if (move2API != other.move2API)
			return false;
		if (move3API != other.move3API)
			return false;
		if (move4API != other.move4API)
			return false;
		if (pokemonAPI != other.pokemonAPI)
			return false;
		if (pokemonId != other.pokemonId)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	public int getPokemonId() {
		return pokemonId;
	}

	public void setPokemonId(int pokemonId) {
		this.pokemonId = pokemonId;
	}

	public int getPokemonAPI() {
		return pokemonAPI;
	}

	public void setPokemonAPI(int pokemonAPI) {
		this.pokemonAPI = pokemonAPI;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getMove1API() {
		return move1API;
	}

	public void setMove1API(int move1api) {
		move1API = move1api;
	}

	public int getMove2API() {
		return move2API;
	}

	public void setMove2API(int move2api) {
		move2API = move2api;
	}

	public int getMove3API() {
		return move3API;
	}

	public void setMove3API(int move3api) {
		move3API = move3api;
	}

	public int getMove4API() {
		return move4API;
	}

	public void setMove4API(int move4api) {
		move4API = move4api;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	
}
