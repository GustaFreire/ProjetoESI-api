CREATE TABLE orientadores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT,
    nome VARCHAR(255),
    email VARCHAR(255),

    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) 
);