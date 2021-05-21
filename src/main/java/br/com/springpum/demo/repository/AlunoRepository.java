package br.com.springpum.demo.repository;

import br.com.springpum.demo.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByNome(String nomeAluno);
}
