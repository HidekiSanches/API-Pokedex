package com.api.pokedex.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvolutionChainDTO {

    @JsonProperty("id")
    private UUID id;
    @JsonProperty("basePokemonId")
    private UUID basePokemonId;
}
