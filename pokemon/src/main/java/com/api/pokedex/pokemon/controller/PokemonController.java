package com.api.pokedex.pokemon.controller;

import com.api.pokedex.pokemon.dto.EvolutionChainDTO;
import com.api.pokedex.pokemon.dto.PokemonDTO;
import com.api.pokedex.pokemon.model.EvolutionChain;
import com.api.pokedex.pokemon.model.Pokemon;
import com.api.pokedex.pokemon.service.EvolutionChainService;
import com.api.pokedex.pokemon.service.PokemonService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<PokemonDTO> savePokemon(@RequestBody PokemonDTO pokemonDTO){
        PokemonDTO savedPokemon = pokemonService.savePokemon(pokemonDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPokemon);
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

    @DeleteMapping("/{Id}")
    public ResponseEntity deleteById(@PathVariable UUID Id) {
        pokemonService.deleteById(Id);
        return ResponseEntity.noContent().build();
    }

}
