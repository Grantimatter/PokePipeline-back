package com.revature.pokepipeline.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="POKEMON")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
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
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, targetEntity= Trainer.class)
	@JoinColumn(name="trainerId")
	@JsonManagedReference
	private Trainer trainer;

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
		result = prime * result + ((trainer == null) ? 0 : trainer.hashCode());
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
		if (trainer == null) {
			if (other.trainer != null)
				return false;
		} else if (!trainer.equals(other.trainer))
			return false;
		return true;
	}
}
