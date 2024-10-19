package br.usp.esi.api.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "AlunoDisciplinasAprovadas")
@Table(name = "aluno_disciplinas_aprovadas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDisciplinasAprovadas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    private String disciplina;

    public AlunoDisciplinasAprovadas(Aluno aluno, String disciplina) {
        this.aluno = aluno;
        this.disciplina = disciplina;
    }
}
