package br.com.springpum.demo.form;

import br.com.springpum.demo.model.Aluno;
import br.com.springpum.demo.repository.AlunoRepository;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AlunoForm {

    @NotNull @NotEmpty @Length(max = 30)
    private String nome;

    @NotNull @NotEmpty @Length(max = 30)
    private String email;

    @NotNull @NotEmpty @Size(min = 1, max = 18)
    private Integer idade;

    public Aluno converter(){
        return new Aluno(nome, email, idade);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}
