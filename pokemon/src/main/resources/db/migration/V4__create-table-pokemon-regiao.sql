CREATE TABLE pokemon_region (
    pokemon_id binary(16),
    region_id binary(16),
    PRIMARY KEY (pokemon_id, region_id),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon (id_pokemon),
    FOREIGN KEY (region_id) REFERENCES region (id)
);