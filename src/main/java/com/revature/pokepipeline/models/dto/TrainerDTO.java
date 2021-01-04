package com.revature.pokepipeline.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class TrainerDTO {
    String trainerName;
    String email;
    String password;
    String description;
}
