package com.revature.PokePipeline.model;

import java.util.List;

public class Pokemon {
	
	private int pokemonId; // primary key
	private int pokemonAPI;
	private double currentHP; // do we need this?
	private int exp;
	private List<Move> moveList;

}
