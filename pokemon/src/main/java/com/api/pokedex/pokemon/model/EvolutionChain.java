package com.api.pokedex.pokemon.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "evolution_chain")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EvolutionChain {

    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id_evolucao", length = 16, columnDefinition = "uuid")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "pokemon_base_id")
    @JsonIgnoreProperties("evolutionChainId")
    private Pokemon basePokemonId;

}
