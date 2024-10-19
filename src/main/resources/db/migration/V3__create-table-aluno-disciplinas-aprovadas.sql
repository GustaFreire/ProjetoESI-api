CREATE TABLE aluno_disciplinas_aprovadas (
    aluno_id BIGINT NOT NULL,
    disciplina VARCHAR(255),
    PRIMARY KEY (aluno_id, disciplina),
    FOREIGN KEY (aluno_id) REFERENCES alunos(id) ON DELETE CASCADE
);