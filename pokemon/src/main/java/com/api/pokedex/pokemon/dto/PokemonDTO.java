package com.api.pokedex.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
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
public class PokemonDTO {

    @JsonProperty("nome")
    private String name;
    @JsonProperty("tipo_primario")
    private String primaryType;
    @JsonProperty("tipo_secundario")
    private String secondaryType;
    @JsonProperty("descricao")
    private String description;
    @JsonProperty("altura")
    private Double height;
    @JsonProperty("peso")
    private Double weight;
    @JsonProperty("regiao_ids")
    private Set<UUID> regionIds;
    @JsonProperty("pokemon_id")
    private UUID basePokemonId;

}
