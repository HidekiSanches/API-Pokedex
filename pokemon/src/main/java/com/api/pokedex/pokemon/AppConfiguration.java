package com.api.pokedex.pokemon;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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

}
