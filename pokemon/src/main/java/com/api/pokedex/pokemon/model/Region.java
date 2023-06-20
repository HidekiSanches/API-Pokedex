package com.api.pokedex.pokemon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "regiao")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Region {

    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "regions")
    @JsonIgnore
    private Set<Pokemon> pokemons = new HashSet<>();

}
