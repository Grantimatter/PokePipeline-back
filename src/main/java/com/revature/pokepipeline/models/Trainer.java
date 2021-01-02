package com.revature.pokepipeline.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

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

@Entity
@Table(name="TRAINERS")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class Trainer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private int trainerId;
	
	@Column(unique=true, nullable = false)
	@NonNull
	private String trainerName;
	@Column(unique=true, nullable = false)
	@NonNull
	private String email;
	@Column(nullable = false)
	@NonNull
	private String password;
	private String description;
	private byte[] profilePicture;

	@OneToMany(mappedBy="trainer", fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Pokemon> pokemonList;

	@OneToMany(mappedBy="trainer", fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Item> itemList;

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
		result = prime * result + trainerId;
		result = prime * result + ((trainerName == null) ? 0 : trainerName.hashCode());
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
		Trainer other = (Trainer) obj;
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
		if (trainerId != other.trainerId)
			return false;
		if (trainerName == null) {
			if (other.trainerName != null)
				return false;
		} else if (!trainerName.equals(other.trainerName))
			return false;
		return true;
	}
}
