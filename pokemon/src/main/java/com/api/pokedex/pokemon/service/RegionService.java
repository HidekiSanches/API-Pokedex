package com.api.pokedex.pokemon.service;

import com.api.pokedex.pokemon.dto.RegionDTO;
import com.api.pokedex.pokemon.model.Pokemon;
import com.api.pokedex.pokemon.model.Region;

import java.util.List;

public interface RegionService {
    RegionDTO saveRegion(RegionDTO regionDTO);

    List<Region> findAll();
}
