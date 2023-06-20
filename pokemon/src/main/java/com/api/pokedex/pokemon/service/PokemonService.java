package com.api.pokedex.pokemon.service;

import com.api.pokedex.pokemon.dto.PokemonDTO;
import com.api.pokedex.pokemon.model.EvolutionChain;
import com.api.pokedex.pokemon.model.Pokemon;

import java.util.List;
import java.util.UUID;

public interface PokemonService {
    PokemonDTO savePokemon(PokemonDTO pokemonDTO);
    List<Pokemon> findAll();
    Pokemon findById(UUID id);
    void deleteById(UUID id);
}
