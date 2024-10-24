CREATE TABLE relatorios (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    aluno_id BIGINT,
    orientador_id BIGINT,
    data_limite DATE,
    prazo_maximo_qualificacao DATE,
    prazo_maximo_dissertacao DATE,
    num_artigos_escrita INT,
    num_artigos_submetidos INT,
    num_artigos_publicados INT,
    resumo_atividades_academicas TEXT,
    resumo_atividades_pesquisa TEXT,
    info_adicional_para_ccp TEXT,
    nome_relatorio VARCHAR(255),
    resultado_ultima_avaliacao VARCHAR(255),
    situacao_relatorio VARCHAR(255),
    necessita_apoio_ccp BOOLEAN,

    FOREIGN KEY (aluno_id) REFERENCES alunos(id),
    FOREIGN KEY (orientador_id) REFERENCES orientadores(id)
);