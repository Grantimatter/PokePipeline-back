package com.revature.PokePipeline.utility;

import com.revature.PokePipeline.models.Type;

public class TypeConversions {
	
	public static Type stringToType(String type) {
		type = type.toLowerCase();
		switch(type) {
		case "bug":
			return Type.BUG;
		case "dark":
			return Type.DARK;
		case "dragon":
			return Type.DRAGON;
		case "electric":
			return Type.ELECTRIC;
		case "fairy":
			return Type.FAIRY;
		case "fighting":
			return Type.FIGHTING;
		case "fire":
			return Type.FIRE;
		case "flying":
			return Type.FLYING;
		case "ghost":
			return Type.GHOST;
		case "grass":
			return Type.GRASS;
		case "ground":
			return Type.GROUND;
		case "ice":
			return Type.ICE;
		case "normal":
			return Type.NORMAL;
		case "poison":
			return Type.POISON;
		case "psychic":
			return Type.PSYCHIC;
		case "rock":
			return Type.ROCK;
		case "steel":
			return Type.STEEL;
		case "water":
			return Type.WATER;
		default:
			return null;
		}
	}
	
	public static String typeToString(Type type) {
		switch(type) {
		case BUG:
			return "Bug";
		case DARK:
			return "Dark";
		case DRAGON:
			return "Dragon";
		case ELECTRIC:
			return "Electric";
		case FAIRY:
			return "Fairy";
		case FIGHTING:
			return "Fighting";
		case FIRE:
			return "Fire";
		case FLYING:
			return "Flying";
		case GHOST:
			return "Ghost";
		case GRASS:
			return "Grass";
		case GROUND:
			return "Ground";
		case ICE:
			return "Ice";
		case NORMAL:
			return "Normal";
		case POISON:
			return "Poison";
		case PSYCHIC:
			return "Psychic";
		case ROCK:
			return "Rock";
		case STEEL:
			return "Steel";
		case WATER:
			return "Water";
		default:
			return null;
		}
	}

}
