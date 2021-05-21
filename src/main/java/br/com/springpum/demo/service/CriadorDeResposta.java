package br.com.springpum.demo.service;
import br.com.springpum.demo.model.Aluno;
import br.com.springpum.demo.model.Avaliacao;
import br.com.springpum.demo.model.RespostaQuestao;

public class CriadorDeResposta {

    private RespostaQuestao respostaQuestao;

    public CriadorDeResposta() {

    }
    public CriadorDeResposta init() {
        this.respostaQuestao = new RespostaQuestao();
        return this;
    }

    public CriadorDeResposta daAvaliacao(Avaliacao avaliacao){
        System.out.println(avaliacao.getNome());
        this.respostaQuestao.setAvaliacao(avaliacao);
        return this;
    }

    public CriadorDeResposta doAluno(Aluno aluno){
        respostaQuestao.setAluno(aluno);
        return this;
    }

    public CriadorDeResposta comANota(Integer nota){
        this.respostaQuestao.setNota(nota);
        return this;
    }

    public RespostaQuestao build(){
        return respostaQuestao;
    }
}
