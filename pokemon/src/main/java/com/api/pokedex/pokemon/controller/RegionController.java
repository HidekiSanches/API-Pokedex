package com.api.pokedex.pokemon.controller;

import com.api.pokedex.pokemon.dto.EvolutionChainDTO;
import com.api.pokedex.pokemon.dto.RegionDTO;
import com.api.pokedex.pokemon.model.Pokemon;
import com.api.pokedex.pokemon.model.Region;
import com.api.pokedex.pokemon.service.RegionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RestControllerAdvice
@RequestMapping("/region")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @RequestMapping("/save")
    public ResponseEntity<RegionDTO> saveRegion(@RequestBody RegionDTO regionDTO){
        RegionDTO savedRegion = regionService.saveRegion(regionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRegion);
    }

    @GetMapping("/")
    public ResponseEntity<List<Region>> findAll() {
        List<Region> regions = regionService.findAll();
        return ResponseEntity.ok(regions);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Region> findById(@PathVariable UUID Id) {
        Region region = regionService.findById(Id);
        return ResponseEntity.ok(region);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity deleteById(@PathVariable UUID Id) {
        regionService.deleteById(Id);
        return ResponseEntity.noContent().build();
    }
}
