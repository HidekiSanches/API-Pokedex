package com.api.pokedex.pokemon.service;

import com.api.pokedex.pokemon.dto.RegionDTO;
import com.api.pokedex.pokemon.exception.GlobalExceptionHandler;
import com.api.pokedex.pokemon.model.Pokemon;
import com.api.pokedex.pokemon.model.Region;
import com.api.pokedex.pokemon.repository.PokemonRepository;
import com.api.pokedex.pokemon.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
        validateRegionDTO(regionDTO);

        if (regionRepository.existsByName(regionDTO.getName())) {
            throw new GlobalExceptionHandler.BadRequestException("Region name must be unique");
        }

        Region region = new Region();
        region.setName(regionDTO.getName());

        if (regionDTO.getPokemonsIds() != null && !regionDTO.getPokemonsIds().isEmpty()) {
            List<Pokemon> pokemonsList = pokemonRepository.findAllById(regionDTO.getPokemonsIds());
            Set<Pokemon> pokemonsSet = new HashSet<>(pokemonsList);
            region.setPokemons(pokemonsSet);
        }

        Region regionSaved = regionRepository.save(region);

        RegionDTO regionDTOSaved = new RegionDTO();
        regionDTOSaved.setName(regionSaved.getName());
        regionDTOSaved.setPokemonsIds(regionSaved.getPokemons().stream().map(Pokemon::getId).collect(Collectors.toSet()));
        return regionDTOSaved;
    }

    private void validateRegionDTO(RegionDTO regionDTO) {
        if (regionDTO.getName() == null || regionDTO.getName().isEmpty()) {
            throw new GlobalExceptionHandler.BadRequestException("Region name is required");
        }
    }

    @Override
    public List<Region> findAll() {
        List<Region> regions = regionRepository.findAll();
        return regions;
    }

    @Override
    public Region findById(UUID Id) {
        Region region = regionRepository.findById(Id)
                .orElseThrow(() -> new GlobalExceptionHandler.NotFoundException("Invalid region ID: " + Id));

        return region;
    }

    @Override
    public void deleteById(UUID Id) {
        if (!regionRepository.existsById(Id)) {
            throw new GlobalExceptionHandler.NotFoundException("Region not found with ID: " + Id);
        }

        regionRepository.deleteById(Id);
    }

}
