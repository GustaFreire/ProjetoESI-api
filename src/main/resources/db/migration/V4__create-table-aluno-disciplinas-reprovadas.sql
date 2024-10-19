CREATE TABLE aluno_disciplinas_reprovadas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    aluno_id BIGINT,
    disciplina VARCHAR(255),
    FOREIGN KEY (aluno_id) REFERENCES alunos(id) ON DELETE CASCADE
);