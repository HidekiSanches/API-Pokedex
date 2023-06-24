ALTER TABLE evolution_chain
ADD CONSTRAINT fk_evolution_chain_pokemon
FOREIGN KEY (pokemon_base_id) REFERENCES pokemon (id_pokemon);