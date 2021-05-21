package br.com.springpum.demo.repository;

import br.com.springpum.demo.model.RespostaQuestao;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import java.util.Collection;

public class RespostaRepository {
    private EntityManager manager;

    public RespostaRepository(EntityManager manager){
        this.manager = manager;
    }

    public Collection<RespostaQuestao> buscaRespostas(Long idAluno){
        return manager.createQuery("select r from RespostaQuestao r where r.aluno.id =    :idAluno",RespostaQuestao.class)
                .setParameter("idAluno",idAluno)
                .getResultList();
    }

}
