package com.revature.pokepipeline.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@Table(name="ITEM")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @EqualsAndHashCode @ToString
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int itemId;

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, targetEntity= Trainer.class)
	@JoinColumn(name="trainerId")
	@JsonManagedReference
	private Trainer trainer;

	private int itemAPI;
}
