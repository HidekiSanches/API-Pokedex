package com.api.pokedex.pokemon.service;

import com.api.pokedex.pokemon.dto.PokemonDTO;
import com.api.pokedex.pokemon.exception.GlobalExceptionHandler;
import com.api.pokedex.pokemon.model.EvolutionChain;
import com.api.pokedex.pokemon.model.Pokemon;
import com.api.pokedex.pokemon.model.Region;
import com.api.pokedex.pokemon.repository.EvolutionChainRepository;
import com.api.pokedex.pokemon.repository.PokemonRepository;
import com.api.pokedex.pokemon.repository.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PokemonServiceImplTest {
    @Mock
    private PokemonRepository pokemonRepository;

    @Mock
    private EvolutionChainRepository evolutionChainRepository;

    @Mock
    private RegionRepository regionRepository;

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSavePokemon_WithInvalidPokemonName() {
        PokemonDTO pokemonDTO = new PokemonDTO();
        pokemonDTO.setName("");
        pokemonDTO.setPrimaryType("Fire");
        pokemonDTO.setDescription("A powerful fire-type Pokemon");

        assertThrows(GlobalExceptionHandler.BadRequestException.class, () -> pokemonService.savePokemon(pokemonDTO));

        verify(pokemonRepository, never()).existsByName(anyString());
        verify(pokemonRepository, never()).save(any(Pokemon.class));
    }

    @Test
    void testSavePokemon_WithInvalidPrimaryType() {
        PokemonDTO pokemonDTO = new PokemonDTO();
        pokemonDTO.setName("Charizard");
        pokemonDTO.setPrimaryType("");
        pokemonDTO.setDescription("A powerful fire-type Pokemon");

        assertThrows(GlobalExceptionHandler.BadRequestException.class, () -> pokemonService.savePokemon(pokemonDTO));

        verify(pokemonRepository, never()).existsByName(anyString());
        verify(pokemonRepository, never()).save(any(Pokemon.class));
    }

    @Test
    void testSavePokemon_WithInvalidDescription() {
        PokemonDTO pokemonDTO = new PokemonDTO();
        pokemonDTO.setName("Charizard");
        pokemonDTO.setPrimaryType("Fire");
        pokemonDTO.setDescription("");

        assertThrows(GlobalExceptionHandler.BadRequestException.class, () -> pokemonService.savePokemon(pokemonDTO));

        verify(pokemonRepository, never()).existsByName(anyString());
        verify(pokemonRepository, never()).save(any(Pokemon.class));
    }

    @Test
    void testSavePokemon_WithExistingPokemonName() {
        PokemonDTO pokemonDTO = new PokemonDTO();
        pokemonDTO.setName("Charizard");
        pokemonDTO.setPrimaryType("Fire");
        pokemonDTO.setDescription("A powerful fire-type Pokemon");

        when(pokemonRepository.existsByName(pokemonDTO.getName())).thenReturn(true);

        assertThrows(GlobalExceptionHandler.BadRequestException.class, () -> pokemonService.savePokemon(pokemonDTO));

        verify(pokemonRepository, times(1)).existsByName(pokemonDTO.getName());
        verify(pokemonRepository, never()).save(any(Pokemon.class));
    }

    private Set<UUID> getRegionIds(Set<Region> regions) {
        Set<UUID> regionIds = new HashSet<>();
        for (Region region : regions) {
            regionIds.add(region.getId());
        }
        return regionIds;
    }


    @Test
    void testFindAll() {
        List<Pokemon> mockPokemons = new ArrayList<>();
        mockPokemons.add(new Pokemon(UUID.randomUUID(), "Pikachu", "Electric", "Electric", "Powerful electric type", 0.7, 12.7, null, null));
        mockPokemons.add(new Pokemon(UUID.randomUUID(), "Charizard", "Fire", "Flying", "Dragon-like Pokemon with fire and flying abilities", 1.7, 90.5, null, null));

        when(pokemonRepository.findAll()).thenReturn(mockPokemons);

        List<Pokemon> pokemons = pokemonService.findAll();

        assertEquals(mockPokemons.size(), pokemons.size());
        assertEquals(mockPokemons.get(0).getName(), pokemons.get(0).getName());
        assertEquals(mockPokemons.get(1).getName(), pokemons.get(1).getName());

        verify(pokemonRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        UUID pokemonId = UUID.randomUUID();
        Pokemon mockPokemon = new Pokemon(UUID.randomUUID(), "Pikachu", "Electric", "Electric", "Powerful electric type", 0.7, 12.7, null, null);


        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(mockPokemon));

        Pokemon pokemon = pokemonService.findById(pokemonId);

        assertEquals(mockPokemon.getId(), pokemon.getId());
        assertEquals(mockPokemon.getName(), pokemon.getName());
        assertEquals(mockPokemon.getPrimaryType(), pokemon.getPrimaryType());
        assertEquals(mockPokemon.getSecondaryType(), pokemon.getSecondaryType());
        assertEquals(mockPokemon.getDescription(), pokemon.getDescription());

        verify(pokemonRepository, times(1)).findById(pokemonId);
    }

    @Test
    void testDeleteById() {
        UUID pokemonId = UUID.randomUUID();

        when(pokemonRepository.existsById(pokemonId)).thenReturn(true);

        pokemonService.deleteById(pokemonId);

        verify(pokemonRepository, times(1)).existsById(pokemonId);
        verify(pokemonRepository, times(1)).deleteById(pokemonId);
    }
}