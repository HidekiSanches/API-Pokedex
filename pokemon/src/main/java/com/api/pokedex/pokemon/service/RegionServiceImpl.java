package com.api.pokedex.pokemon.service;

import com.api.pokedex.pokemon.dto.RegionDTO;
import com.api.pokedex.pokemon.model.Pokemon;
import com.api.pokedex.pokemon.model.Region;
import com.api.pokedex.pokemon.repository.PokemonRepository;
import com.api.pokedex.pokemon.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RegionServiceImpl implements RegionService{
    private final RegionRepository regionRepository;
    private final PokemonRepository pokemonRepository;

    public RegionServiceImpl(RegionRepository regionRepository, PokemonRepository pokemonRepository){
        this.regionRepository = regionRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public RegionDTO saveRegion(RegionDTO regionDTO) {
        Region region = new Region();
        region.setName(regionDTO.getName());

        if(regionDTO.getPokemonsIds() != null && !regionDTO.getPokemonsIds().isEmpty()){
            Set<Pokemon> pokemons = (Set<Pokemon>) pokemonRepository.findAllById(regionDTO.getPokemonsIds());
            region.setPokemons(pokemons);
        }

        Region regionSaved = regionRepository.save(region);

        RegionDTO regionDTOSaved = new RegionDTO();
        regionDTOSaved.setName(regionSaved.getName());
        regionDTOSaved.setPokemonsIds(regionSaved.getPokemons().stream().map(Pokemon::getId).collect(Collectors.toSet()));
        return regionDTOSaved;
    }

    @Override
    public List<Region> findAll() {
        List<Region> regions = regionRepository.findAll();
        return regions;
    }

}
