package com.api.pokedex.pokemon.repository;

import com.api.pokedex.pokemon.model.EvolutionChain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface EvolutionChainRepository extends JpaRepository<EvolutionChain, UUID> {
    Optional<EvolutionChain> findById(UUID pokemonId);
}
