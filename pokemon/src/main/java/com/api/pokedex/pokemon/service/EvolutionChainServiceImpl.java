package com.api.pokedex.pokemon.service;

import com.api.pokedex.pokemon.dto.EvolutionChainDTO;
import com.api.pokedex.pokemon.dto.PokemonDTO;
import com.api.pokedex.pokemon.exception.GlobalExceptionHandler;
import com.api.pokedex.pokemon.model.EvolutionChain;
import com.api.pokedex.pokemon.model.Pokemon;
import com.api.pokedex.pokemon.repository.EvolutionChainRepository;
import com.api.pokedex.pokemon.repository.PokemonRepository;
import com.api.pokedex.pokemon.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EvolutionChainServiceImpl implements EvolutionChainService {

    private final EvolutionChainRepository evolutionChainRepository;
    private final PokemonRepository pokemonRepository;

    public EvolutionChainServiceImpl(EvolutionChainRepository evolutionChainRepository, PokemonRepository pokemonRepository) {
        this.evolutionChainRepository = evolutionChainRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public EvolutionChainDTO saveEvolutionChain(EvolutionChainDTO evolutionChainDTO) {
        Pokemon basePokemon = pokemonRepository.findById(evolutionChainDTO.getBasePokemonId())
                .orElseThrow(() -> new GlobalExceptionHandler.NotFoundException("Base Pokemon not found"));

        EvolutionChain evolutionChain = new EvolutionChain();
        evolutionChain.setId(basePokemon.getId());
        evolutionChain.setBasePokemonId(basePokemon);

        EvolutionChain savedEvolutionChain = evolutionChainRepository.save(evolutionChain);
        return mapEvolutionChainEntityToDTO(savedEvolutionChain);
    }

    private EvolutionChainDTO mapEvolutionChainEntityToDTO(EvolutionChain evolutionChain) {
        EvolutionChainDTO evolutionChainDTO = new EvolutionChainDTO();
        evolutionChainDTO.setId(evolutionChain.getId());
        evolutionChainDTO.setBasePokemonId(evolutionChain.getBasePokemonId().getId());
        return evolutionChainDTO;
    }

    @Override
    public EvolutionChain findById(UUID Id) {
        EvolutionChain evolutionChain = evolutionChainRepository.findById(Id)
                .orElseThrow(() -> new GlobalExceptionHandler.NotFoundException("Invalid evolution chain ID: " + Id));

        return evolutionChain;
    }

    @Override
    public List<EvolutionChain> findAll() {
        List<EvolutionChain> evolutions = evolutionChainRepository.findAll();
        return evolutions;
    }

    @Override
    public void deleteById(UUID Id) {
        if (!evolutionChainRepository.existsById(Id)) {
            throw new GlobalExceptionHandler.NotFoundException("Evolution Chain not found with ID: " + Id);
        }

        evolutionChainRepository.deleteById(Id);
    }

}
