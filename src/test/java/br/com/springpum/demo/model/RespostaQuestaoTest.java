package br.com.springpum.demo.model;

import br.com.springpum.demo.service.CriadorDeResposta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestExecution;


import static org.junit.jupiter.api.Assertions.*;

class RespostaQuestaoTest {

    private Aluno aluno;
    private Avaliacao avaliacao;
    //private RespostaQuestao respostaQuestao;
    //@Test(expected = RuntimeException.class) versão 4 ou < do JUnit

    @BeforeEach
    public void init(){
        this.aluno = new Aluno("Carine", "cbbathaglini@gmail.com", 23);
        this.avaliacao = new Avaliacao("Avaliação 1");
        //this.respostaQuestao = new RespostaQuestao();
    }

    @Test
    void naoDeveResponderQuestaoSeAvaliacaoNaoExiste() throws IllegalArgumentException {
        //A avaliação não pode ser nula
        Assertions.assertThrows(IllegalArgumentException.class, () ->
             new RespostaQuestao(null, aluno, 3),
                "A avaliação não pode ser nula!");
    }

    @Test
    void naoDeveResponderQuestaoSeAlunoNaoExiste(){
        //O aluno não pode ser nulo
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                        new RespostaQuestao(avaliacao, null, 8),
                "O aluno não pode ser nulo!");

    }

    @Test
    void notaNaoPodeSerMenorQueZero(){
        //A nota não pode ser menor que zero
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new RespostaQuestao(avaliacao, aluno, -1);
        });

    }


    @Test
    void notaNaoPodeSerMaiorQue10(){
        //A nota não pode ser maior que 10
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new RespostaQuestao(avaliacao, aluno, 12);
        });

    }

    @Test
    void notaNoIntervaloPermitidoDe0ate10(){

        Avaliacao avaliacao2 = new Avaliacao("Avaliação 2");
        Aluno aluno2 = new Aluno("Carine", "cbbathaglini@gmail.com", 23);
        RespostaQuestao respostaQuestao2 = new RespostaQuestao(avaliacao2, aluno2, 10);
        //System.out.println(respostaQuestao2.toString());
        Assertions.assertTrue(respostaQuestao2.getNota() >= 0 && respostaQuestao2.getNota() <= 10);
    }

}