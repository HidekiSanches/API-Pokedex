package com.api.pokedex.pokemon.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfiguration {
    @Bean
    @Profile("development")
    public CommandLineRunner executarDevelopment(){
        return args -> {
            System.out.println("RODANDO O AMBIENTE DE DESENVOLVIMENTO");
        };
    }

    @Bean
    @Profile("production")
    public CommandLineRunner executarProduction(){
        return args -> {
            System.out.println("RODANDO O AMBIENTE DE PRODUÇÃO");
        };
    }

    @Bean
    @Profile("development")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
