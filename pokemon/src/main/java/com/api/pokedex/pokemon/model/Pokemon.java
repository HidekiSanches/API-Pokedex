package com.api.pokedex.pokemon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "pokemon")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {

    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id_pokemon", length = 16)
    private UUID id;
    @Column(name = "nome", length = 100, unique = true, nullable = false)
    private String name;
    @Column(name = "tipo_primario",length = 50, nullable = false)
    private String primaryType;
    @Column(name = "tipo_secundario", length = 50, nullable = false)
    private String secondaryType;
    @Column(name = "descricao", length = 500, nullable = false)
    private String description;
    @Column(name = "altura")
    private Double height;
    @Column(name = "peso")
    private Double weight;
    @ManyToOne
    @JoinColumn(name = "evolucao_id", nullable = true)
    private EvolutionChain evolutionChainId;
    @ManyToMany
    @JoinTable(
            name = "pokemon_regiao",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "regiao_id")
    )
    private Set<Region> regions;

}
