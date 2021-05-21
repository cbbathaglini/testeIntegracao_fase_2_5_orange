package br.com.springpum.demo.dto;

import br.com.springpum.demo.model.Aluno;

import java.util.List;
import java.util.stream.Collectors;

public class AlunoDTO {
    private String nome;
    private String email;

    public AlunoDTO() {

    }

    public AlunoDTO(Aluno aluno) {
        this.nome = aluno.getNome();
        this.email = aluno.getEmail();
    }

    public static List<AlunoDTO> converter(List<Aluno> alunos){
        return  alunos.stream().map(AlunoDTO::new).collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
