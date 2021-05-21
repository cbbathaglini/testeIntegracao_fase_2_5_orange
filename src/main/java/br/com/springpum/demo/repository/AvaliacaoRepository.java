package br.com.springpum.demo.repository;

import br.com.springpum.demo.model.Aluno;
import br.com.springpum.demo.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
}
