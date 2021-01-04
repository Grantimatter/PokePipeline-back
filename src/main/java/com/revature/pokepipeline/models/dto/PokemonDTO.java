package com.revature.pokepipeline.models.dto;

import com.revature.pokepipeline.models.Trainer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data @AllArgsConstructor @NoArgsConstructor
public class PokemonDTO {
    int pokemonId;
    int pokemonAPI;
    int currentHP;
    int experience;
    int move1API;
    int move2API;
    int move3API;
    int move4API;
    Trainer trainer;
}
