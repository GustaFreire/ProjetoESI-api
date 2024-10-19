CREATE TABLE alunos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT,
    tipo_curso VARCHAR(255), 
    nome VARCHAR(255),
    email VARCHAR(255),
    nusp VARCHAR(20),
    rg VARCHAR(20),
    nome_orientador VARCHAR(255),
    local_nascimento VARCHAR(255),
    nacionalidade VARCHAR(100),
    link_lattes VARCHAR(255),
    data_nascimento DATE,
    data_ultima_att_lattes DATE,
    data_matricula DATE,
    data_aprovacao_exame_qualificacao DATE,
    data_aprovacao_exame_proficiencia DATE,

    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) 
);