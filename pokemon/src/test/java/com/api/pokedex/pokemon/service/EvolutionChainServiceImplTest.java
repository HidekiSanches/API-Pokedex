package com.api.pokedex.pokemon.service;

import com.api.pokedex.pokemon.dto.EvolutionChainDTO;
import com.api.pokedex.pokemon.exception.GlobalExceptionHandler;
import com.api.pokedex.pokemon.model.EvolutionChain;
import com.api.pokedex.pokemon.model.Pokemon;
import com.api.pokedex.pokemon.repository.EvolutionChainRepository;
import com.api.pokedex.pokemon.repository.PokemonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EvolutionChainServiceImplTest {

    @Mock
    private PokemonRepository pokemonRepository;

    @Mock
    private EvolutionChainRepository evolutionChainRepository;

    @InjectMocks
    private EvolutionChainServiceImpl evolutionChainService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveEvolutionChain_WithBasePokemonNotFound() {
        UUID basePokemonId = UUID.randomUUID();
        EvolutionChainDTO evolutionChainDTO = new EvolutionChainDTO();
        evolutionChainDTO.setBasePokemonId(basePokemonId);

        when(pokemonRepository.findById(basePokemonId)).thenReturn(Optional.empty());

        assertThrows(GlobalExceptionHandler.NotFoundException.class, () -> evolutionChainService.saveEvolutionChain(evolutionChainDTO));

        verify(pokemonRepository, times(1)).findById(basePokemonId);
        verify(evolutionChainRepository, never()).save(any(EvolutionChain.class));
    }

    @Test
    public void testSaveEvolutionChain_WithInvalidEvolutionChain() {
        UUID basePokemonId = UUID.randomUUID();
        EvolutionChainDTO evolutionChainDTO = new EvolutionChainDTO();
        evolutionChainDTO.setBasePokemonId(basePokemonId);

        when(pokemonRepository.findById(evolutionChainDTO.getBasePokemonId())).thenReturn(Optional.of(new Pokemon()));
        when(evolutionChainRepository.save(any(EvolutionChain.class))).thenThrow(GlobalExceptionHandler.NotFoundException.class);

        assertThrows(GlobalExceptionHandler.NotFoundException.class, () -> evolutionChainService.saveEvolutionChain(evolutionChainDTO));

        verify(pokemonRepository, times(1)).findById(evolutionChainDTO.getBasePokemonId());
        verify(evolutionChainRepository, times(1)).save(any(EvolutionChain.class));
    }

    @Test
    void testFindById_WithValidId() {
        UUID evolutionChainId = UUID.randomUUID();
        EvolutionChain expectedEvolutionChain = new EvolutionChain();
        expectedEvolutionChain.setId(evolutionChainId);

        when(evolutionChainRepository.findById(evolutionChainId)).thenReturn(Optional.of(expectedEvolutionChain));

        EvolutionChain result = evolutionChainService.findById(evolutionChainId);

        assertEquals(expectedEvolutionChain, result);

        verify(evolutionChainRepository, times(1)).findById(evolutionChainId);
    }

    @Test
    void testFindById_WithInvalidId() {
        UUID invalidId = UUID.randomUUID();

        when(evolutionChainRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(GlobalExceptionHandler.NotFoundException.class, () -> evolutionChainService.findById(invalidId));

        verify(evolutionChainRepository, times(1)).findById(invalidId);
    }

    @Test
    void testFindAll() {
        EvolutionChain evolutionChain1 = new EvolutionChain();
        EvolutionChain evolutionChain2 = new EvolutionChain();
        List<EvolutionChain> expectedEvolutions = Arrays.asList(evolutionChain1, evolutionChain2);

        when(evolutionChainRepository.findAll()).thenReturn(expectedEvolutions);

        List<EvolutionChain> result = evolutionChainService.findAll();

        assertEquals(expectedEvolutions, result);

        verify(evolutionChainRepository, times(1)).findAll();
    }

    @Test
    void testDeleteById_WithExistingId() {
        UUID existingId = UUID.randomUUID();

        when(evolutionChainRepository.existsById(existingId)).thenReturn(true);

        evolutionChainService.deleteById(existingId);

        verify(evolutionChainRepository, times(1)).existsById(existingId);
        verify(evolutionChainRepository, times(1)).deleteById(existingId);
    }

    @Test
    void testDeleteById_WithNonExistingId() {
        UUID nonExistingId = UUID.randomUUID();

        when(evolutionChainRepository.existsById(nonExistingId)).thenReturn(false);

        assertThrows(GlobalExceptionHandler.NotFoundException.class, () -> evolutionChainService.deleteById(nonExistingId));

        verify(evolutionChainRepository, times(1)).existsById(nonExistingId);
        verify(evolutionChainRepository, never()).deleteById(any(UUID.class));
    }
}