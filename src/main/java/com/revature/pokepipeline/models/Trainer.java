package com.revature.pokepipeline.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
@Data @AllArgsConstructor @NoArgsConstructor
public class Trainer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int trainerId;
	
	@Column(unique=true, nullable = false)
	private String trainerName;
	@Column(unique=true, nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column
	private String description;

	@OneToMany(mappedBy="trainer")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	private List<Pokemon> pokemonList;

	@OneToMany(mappedBy="trainer")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	private List<Item> itemList;

}
