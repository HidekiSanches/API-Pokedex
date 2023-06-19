package com.api.pokedex.pokemon.service;

import com.api.pokedex.pokemon.dto.EvolutionChainDTO;
import com.api.pokedex.pokemon.model.EvolutionChain;

import java.util.List;
import java.util.UUID;

public interface EvolutionChainService {

    EvolutionChainDTO saveEvolutionChain(EvolutionChainDTO evolutionChainDTO);
    EvolutionChain findById(UUID id);
    List<EvolutionChain> findAll();
}
