package com.api.pokedex.pokemon.repository;

import com.api.pokedex.pokemon.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RegionRepository extends JpaRepository<Region, UUID> {
}
