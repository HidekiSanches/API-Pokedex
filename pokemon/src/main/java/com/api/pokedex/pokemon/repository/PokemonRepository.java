package com.api.pokedex.pokemon.repository;

import com.api.pokedex.pokemon.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PokemonRepository extends JpaRepository<Pokemon, UUID> {
}
