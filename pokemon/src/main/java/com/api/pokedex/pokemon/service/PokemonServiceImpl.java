package com.api.pokedex.pokemon.service;

import com.api.pokedex.pokemon.dto.EvolutionChainDTO;
import com.api.pokedex.pokemon.dto.PokemonDTO;
import com.api.pokedex.pokemon.model.EvolutionChain;
import com.api.pokedex.pokemon.model.Pokemon;
import com.api.pokedex.pokemon.model.Region;
import com.api.pokedex.pokemon.repository.EvolutionChainRepository;
import com.api.pokedex.pokemon.repository.PokemonRepository;
import com.api.pokedex.pokemon.repository.RegionRepository;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.util.*;

@Service
public class PokemonServiceImpl implements PokemonService {
    private final PokemonRepository pokemonRepository;
    private final RegionRepository regionRepository;
    private final EvolutionChainRepository evolutionChainRepository;

    public PokemonServiceImpl(PokemonRepository pokemonRepository, EvolutionChainRepository evolutionChainRepository, RegionRepository regionRepository) {
        this.pokemonRepository = pokemonRepository;
        this.evolutionChainRepository = evolutionChainRepository;
        this.regionRepository = regionRepository;
    }

    @Override
    public PokemonDTO savePokemon(PokemonDTO pokemonDTO) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDTO.getName());
        pokemon.setPrimaryType(pokemonDTO.getPrimaryType());
        pokemon.setSecondaryType(pokemonDTO.getSecondaryType());
        pokemon.setDescription(pokemonDTO.getDescription());
        pokemon.setHeight(pokemonDTO.getHeight());
        pokemon.setWeight(pokemonDTO.getWeight());
        pokemon.setEvolutionChainId(getEvolutionChainById(pokemonDTO.getBasePokemonId()) == null ? null : getEvolutionChainById(pokemonDTO.getBasePokemonId()));
        pokemon.setRegions(getRegionsByIds(pokemonDTO.getRegionIds()));

        Pokemon savedPokemon = pokemonRepository.save(pokemon);
        return mapPokemonToDTO(savedPokemon);
    }

    private EvolutionChain getEvolutionChainById(UUID evolutionChainId) {
        if (evolutionChainId != null) {
            return evolutionChainRepository.findById(evolutionChainId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid evolution chain ID: " + evolutionChainId));
        } else {
            return null;
        }
    }

    private Set<Region> getRegionsByIds(Set<UUID> regionIds) {
        Set<Region> regions = new HashSet<>();
        if (regionIds != null) {
            for (UUID regionId : regionIds) {
                Region region = regionRepository.findById(regionId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid region ID: " + regionId));
                regions.add(region);
            }
        }
        return regions;
    }

    private PokemonDTO mapPokemonToDTO(Pokemon pokemon) {
        PokemonDTO pokemonDTO = new PokemonDTO();
        pokemonDTO.setName(pokemon.getName());
        pokemonDTO.setPrimaryType(pokemon.getPrimaryType());
        pokemonDTO.setSecondaryType(pokemon.getSecondaryType());
        pokemonDTO.setDescription(pokemon.getDescription());
        pokemonDTO.setHeight(pokemon.getHeight());
        pokemonDTO.setWeight(pokemon.getWeight());
        pokemonDTO.setRegionIds(getRegionIds(pokemon.getRegions()));

        EvolutionChain evolutionChain = pokemon.getEvolutionChainId();
        if (evolutionChain != null) {
            pokemonDTO.setBasePokemonId(evolutionChain.getId());
        } else {
            pokemonDTO.setBasePokemonId(null);
        }

        return pokemonDTO;
    }

    private Set<UUID> getRegionIds(Set<Region> regions) {
        Set<UUID> regionIds = new HashSet<>();
        if (regions != null) {
            for (Region region : regions) {
                regionIds.add(region.getId());
            }
        }
        return regionIds;
    }

    @Override
    public List<Pokemon> findAll() {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        return pokemons;
    }

    @Override
    public Pokemon findById(UUID Id) {
        Pokemon pokemon = pokemonRepository.findById(Id)
                .orElseThrow();

        return pokemon;
    }

    @Override
    public void deleteById(UUID Id) {
        pokemonRepository.deleteById(Id);
    }

}
