package com.api.pokedex.pokemon.repository;

import com.api.pokedex.pokemon.model._User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<_User, UUID> {

    Optional<_User> findByUsername(String username);

}
