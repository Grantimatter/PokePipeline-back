package com.revature.pokepipeline.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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
@Data @AllArgsConstructor @NoArgsConstructor
public class Pokemon {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int pokemonId;
	@Column
	private int pokemonAPI;
	@Column
	private int currentHP;
	@Column
	private int experience;
	@Column
	private int move1API;
	@Column
	private int move2API;
	@Column
	private int move3API;
	@Column
	private int move4API;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="trainerId")
	@JsonBackReference
	private Trainer trainer;
}
