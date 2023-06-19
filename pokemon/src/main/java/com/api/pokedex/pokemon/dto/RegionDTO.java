package com.api.pokedex.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegionDTO {

    @JsonProperty("nome")
    private String name;
    @JsonProperty("pokemon_ids")
    private Set<UUID> pokemonsIds;

}
