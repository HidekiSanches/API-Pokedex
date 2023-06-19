package com.api.pokedex.pokemon.controller;

import com.api.pokedex.pokemon.dto.EvolutionChainDTO;
import com.api.pokedex.pokemon.model.EvolutionChain;
import com.api.pokedex.pokemon.model.Pokemon;
import com.api.pokedex.pokemon.service.EvolutionChainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/evolution-chains")
public class EvolutionChainController {

    private final EvolutionChainService evolutionChainService;

    public EvolutionChainController(EvolutionChainService evolutionChainService) {
        this.evolutionChainService = evolutionChainService;
    }

    @PostMapping("/save")
    public ResponseEntity<EvolutionChainDTO> saveEvolutionChain(@RequestBody EvolutionChainDTO evolutionChainDTO) {
        EvolutionChainDTO savedEvolutionChain = evolutionChainService.saveEvolutionChain(evolutionChainDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvolutionChain);
    }

    @GetMapping("/{evolutionChainId}")
    public ResponseEntity<EvolutionChain> findById(@PathVariable UUID evolutionChainId) {
        EvolutionChain evolutionChain = evolutionChainService.findById(evolutionChainId);
        return ResponseEntity.ok(evolutionChain);
    }

    @GetMapping("/")
    public ResponseEntity<List<EvolutionChain>> findAll() {
        List<EvolutionChain> evolutions = evolutionChainService.findAll();
        return ResponseEntity.ok(evolutions);
    }

}
