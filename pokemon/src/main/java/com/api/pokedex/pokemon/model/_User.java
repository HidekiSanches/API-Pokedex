package com.api.pokedex.pokemon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class _User {

    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id_user", length = 16, columnDefinition = "uuid")
    private UUID id;
    @Column(name = "username", length = 100, nullable = false)
    private String username;
    @Column(name = "password", length = 100, nullable = false)
    private String password;

}
