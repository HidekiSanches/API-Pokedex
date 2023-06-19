package com.api.pokedex.pokemon.controller;

import com.api.pokedex.pokemon.dto.RegionDTO;
import com.api.pokedex.pokemon.model.Pokemon;
import com.api.pokedex.pokemon.model.Region;
import com.api.pokedex.pokemon.service.RegionService;
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
    public RegionDTO saveRegion(@RequestBody RegionDTO regionDTO){
        return regionService.saveRegion(regionDTO);
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


}
