CREATE TABLE pokemon_regiao (
    pokemon_id binary(16),
    regiao_id binary(16),
    PRIMARY KEY (pokemon_id, regiao_id),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon (id_pokemon),
    FOREIGN KEY (regiao_id) REFERENCES region (id)
);