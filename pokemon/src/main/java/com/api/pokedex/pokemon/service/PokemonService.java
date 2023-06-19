package com.api.pokedex.pokemon.service;

import com.api.pokedex.pokemon.dto.PokemonDTO;
import com.api.pokedex.pokemon.model.Pokemon;

import java.util.List;

public interface PokemonService {
    PokemonDTO savePokemon(PokemonDTO pokemonDTO);

    List<Pokemon> findAll();
}
