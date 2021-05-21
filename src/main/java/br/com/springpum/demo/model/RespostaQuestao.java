package br.com.springpum.demo.model;

import javax.persistence.*;

@Entity
public class RespostaQuestao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Avaliacao avaliacao;

    @OneToOne
    private Aluno aluno;

    private int nota;


    public RespostaQuestao() {
    }

    public RespostaQuestao(Avaliacao avaliacao, Aluno aluno, int nota) {

        if (avaliacao == null) {
            throw new IllegalArgumentException("A avaliação não pode ser nula");
        }

        if (aluno == null) {
            throw new IllegalArgumentException("O aluno não pode ser nulo");
        }

        if (nota < 0) {
            throw new IllegalArgumentException("A nota não pode ser menor que zero");
        }

        if (nota > 10) {
            throw new IllegalArgumentException("A nota não pode ser maior que10");
        }

        //resto do código de atribuição aqui
        this.avaliacao = avaliacao;
        this.aluno = aluno;
        this.nota = nota;
    }


    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "RespostaQuestao{" +
                "id=" + id +
                ", avaliacao=" + avaliacao +
                ", aluno=" + aluno +
                ", nota=" + nota +
                '}';
    }
}
