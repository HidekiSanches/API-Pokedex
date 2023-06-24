CREATE TABLE pokemon (
    id_pokemon binary(16),
    nome VARCHAR(100) NOT NULL,
    tipo_primario VARCHAR(50) NOT NULL,
    tipo_secundario VARCHAR(50) NOT NULL,
    descricao VARCHAR(500) NOT NULL,
    altura DOUBLE,
    peso DOUBLE,
    evolucao_id binary(16),
    PRIMARY KEY (id_pokemon),
    CONSTRAINT fk_evolucao_id FOREIGN KEY (evolucao_id) REFERENCES evolution_chain (id_evolucao)
);