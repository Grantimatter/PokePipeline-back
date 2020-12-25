package com.revature.PokePipeline.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pokemon {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pokemonId;
	
	private int pokemonAPI;
	private double currentHP;
	private int exp;
	private int move1API;
	private int move2API;
	private int move3API;
	private int move4API;
	
	public Pokemon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pokemon(int pokemonId, int pokemonAPI, double currentHP, int exp, int move1api, int move2api, int move3api,
			int move4api) {
		super();
		this.pokemonId = pokemonId;
		this.pokemonAPI = pokemonAPI;
		this.currentHP = currentHP;
		this.exp = exp;
		move1API = move1api;
		move2API = move2api;
		move3API = move3api;
		move4API = move4api;
	}

	public Pokemon(int pokemonAPI, double currentHP, int exp, int move1api, int move2api, int move3api, int move4api) {
		super();
		this.pokemonAPI = pokemonAPI;
		this.currentHP = currentHP;
		this.exp = exp;
		move1API = move1api;
		move2API = move2api;
		move3API = move3api;
		move4API = move4api;
	}

	@Override
	public String toString() {
		return "Pokemon [pokemonId=" + pokemonId + ", pokemonAPI=" + pokemonAPI + ", currentHP=" + currentHP + ", exp="
				+ exp + ", move1API=" + move1API + ", move2API=" + move2API + ", move3API=" + move3API + ", move4API="
				+ move4API + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(currentHP);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + exp;
		result = prime * result + move1API;
		result = prime * result + move2API;
		result = prime * result + move3API;
		result = prime * result + move4API;
		result = prime * result + pokemonAPI;
		result = prime * result + pokemonId;
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
		if (exp != other.exp)
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

	public double getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(double currentHP) {
		this.currentHP = currentHP;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
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

}
