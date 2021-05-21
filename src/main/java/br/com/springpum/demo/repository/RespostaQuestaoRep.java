package br.com.springpum.demo.repository;

import br.com.springpum.demo.model.RespostaQuestao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RespostaQuestaoRep extends JpaRepository<RespostaQuestao,Long> {

    @Query("select r from RespostaQuestao r where r.aluno.id = :idAluno")
    List<RespostaQuestao> buscarTodasPorIdAluno(Long idAluno);

    @Query("select avg(r.nota) from RespostaQuestao r where r.aluno.id = :idAluno")
    Double mediaNotaPorIdAluno(Long idAluno);

    @Query("select r.aluno.id, avg(r.nota) from RespostaQuestao r group by r.aluno.id")
    List<Object[]> mediaNotaTodos();

    @Query("select r from RespostaQuestao r where r.aluno.id = :idAluno and r.id = :idResposta")
    RespostaQuestao respostaEhDoAluno(Long idResposta,Long idAluno);
}
