package com.api.pokedex.pokemon.service;

import com.api.pokedex.pokemon.dto.RegionDTO;
import com.api.pokedex.pokemon.exception.GlobalExceptionHandler;
import com.api.pokedex.pokemon.model.Pokemon;
import com.api.pokedex.pokemon.model.Region;
import com.api.pokedex.pokemon.repository.PokemonRepository;
import com.api.pokedex.pokemon.repository.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegionServiceImplTest {

    @Mock
    private RegionRepository regionRepository;

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private RegionServiceImpl regionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testSaveRegion() {
        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setName("Kanto");
        regionDTO.setPokemonsIds(new HashSet<>(Arrays.asList(UUID.randomUUID(), UUID.randomUUID())));

        when(regionRepository.existsByName(regionDTO.getName())).thenReturn(false);

        Set<Pokemon> existingPokemons = new HashSet<>();
        existingPokemons.add(new Pokemon(UUID.randomUUID(), "Pikachu", "Electric", "Powerful electric type", null, null, null, null, null));
        existingPokemons.add(new Pokemon(UUID.randomUUID(), "Charizard", "Fire", "Flying", "Dragon-like Pokemon with fire and flying abilities", null, null, null, null));
        when(pokemonRepository.findAllById(regionDTO.getPokemonsIds())).thenReturn(new ArrayList<>(existingPokemons));

        Region savedRegion = new Region(UUID.randomUUID(), regionDTO.getName(), existingPokemons);
        when(regionRepository.save(any(Region.class))).thenReturn(savedRegion);

        RegionDTO savedRegionDTO = regionService.saveRegion(regionDTO);

        verify(regionRepository).existsByName(regionDTO.getName());
        verify(pokemonRepository).findAllById(regionDTO.getPokemonsIds());
        verify(regionRepository).save(any(Region.class));

        assertNotNull(savedRegionDTO);
        assertEquals(savedRegion.getName(), savedRegionDTO.getName());
        assertEquals(savedRegion.getPokemons().stream().map(Pokemon::getId).collect(Collectors.toSet()), savedRegionDTO.getPokemonsIds());
    }

    private List<Pokemon> getMockPokemons() {
        List<Pokemon> mockPokemons = new ArrayList<>();

        UUID pikachuId = UUID.randomUUID();
        Pokemon pikachu = new Pokemon(pikachuId, "Pikachu", "Electric", "Electric", "Powerful electric type", 0.7, 12.7, null, null);
        mockPokemons.add(pikachu);

        UUID charizardId = UUID.randomUUID();
        Pokemon charizard = new Pokemon(charizardId, "Charizard", "Fire", "Flying", "Dragon-like Pokemon with fire and flying abilities", 1.7, 90.5, null, null);
        mockPokemons.add(charizard);

        return mockPokemons;
    }

    @Test
    void testFindAll() {
        List<Region> mockRegions = new ArrayList<>();
        mockRegions.add(new Region(UUID.randomUUID(), "Kanto", new HashSet<>()));
        mockRegions.add(new Region(UUID.randomUUID(), "Johto", new HashSet<>()));
        mockRegions.add(new Region(UUID.randomUUID(), "Hoenn", new HashSet<>()));

        Mockito.when(regionRepository.findAll()).thenReturn(mockRegions);

        List<Region> result = regionService.findAll();

        Mockito.verify(regionRepository).findAll();

        assertEquals(mockRegions, result);
    }

    @Test
    void testFindById_ValidId_ReturnsRegion() {
        UUID regionId = UUID.randomUUID();
        Region mockRegion = new Region(regionId, "Kanto", new HashSet<>());

        Mockito.when(regionRepository.findById(regionId)).thenReturn(Optional.of(mockRegion));

        Region result = regionService.findById(regionId);

        Mockito.verify(regionRepository).findById(regionId);

        assertEquals(mockRegion, result);
    }

    @Test
    void testFindById_InvalidId_ThrowsNotFoundException() {
        UUID invalidId = UUID.randomUUID();

        Mockito.when(regionRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(GlobalExceptionHandler.NotFoundException.class, () -> regionService.findById(invalidId));

        Mockito.verify(regionRepository).findById(invalidId);
    }

    @Test
    void testDeleteById_ExistingId_DeletesRegion() {
        UUID regionId = UUID.randomUUID();
        Region mockRegion = new Region(regionId, "Kanto", new HashSet<>());

        Mockito.when(regionRepository.existsById(regionId)).thenReturn(true);

        regionService.deleteById(regionId);

        Mockito.verify(regionRepository).existsById(regionId);
        Mockito.verify(regionRepository).deleteById(regionId);
    }

    @Test
    void testDeleteById_NonExistingId_ThrowsNotFoundException() {
        UUID invalidId = UUID.randomUUID();

        Mockito.when(regionRepository.existsById(invalidId)).thenReturn(false);

        assertThrows(GlobalExceptionHandler.NotFoundException.class, () -> regionService.deleteById(invalidId));

        Mockito.verify(regionRepository).existsById(invalidId);
        Mockito.verify(regionRepository, Mockito.never()).deleteById(Mockito.any(UUID.class));
    }
}