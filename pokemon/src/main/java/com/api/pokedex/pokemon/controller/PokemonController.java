package com.api.pokedex.pokemon.controller;

import com.api.pokedex.pokemon.dto.EvolutionChainDTO;
import com.api.pokedex.pokemon.dto.PokemonDTO;
import com.api.pokedex.pokemon.model.EvolutionChain;
import com.api.pokedex.pokemon.model.Pokemon;
import com.api.pokedex.pokemon.service.EvolutionChainService;
import com.api.pokedex.pokemon.service.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RestControllerAdvice
@RequestMapping("/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;
    private final EvolutionChainService evolutionChainService;

    public PokemonController(PokemonService pokemonService, EvolutionChainService evolutionChainService){
        this.pokemonService = pokemonService;
        this.evolutionChainService = evolutionChainService;
    }

    @PostMapping("/save")
    public PokemonDTO savePokemon(@RequestBody PokemonDTO pokemonDTO){
        return pokemonService.savePokemon(pokemonDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<Pokemon>> findAll() {
        List<Pokemon> pokemons = pokemonService.findAll();
        return ResponseEntity.ok(pokemons);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Pokemon> findById(@PathVariable UUID Id) {
        Pokemon pokemon = pokemonService.findById(Id);
        return ResponseEntity.ok(pokemon);
    }

}
