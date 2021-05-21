package br.com.springpum.demo.model;

import br.com.springpum.demo.repository.AlunoRepository;
import br.com.springpum.demo.repository.AvaliacaoRepository;
import br.com.springpum.demo.repository.RespostaQuestaoRep;
import br.com.springpum.demo.repository.RespostaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RespostasExercicioFinalTest {
    @Autowired
    private RespostaQuestaoRep respostaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @BeforeEach
    public void init(){

        Aluno aluno1 = new Aluno( "Monica Geller", "monica_geller@gmail.com",28);
        Aluno aluno2 = new Aluno( "Ross Geller", "ross_geller@gmail.com",29);
        Aluno aluno3 = new Aluno("Chandler Bing", "chandler_bing@gmail.com",28);
        Aluno aluno4 = new Aluno("Phoebe Buffay", "phoebe_buffay@gmail.com",30);
        Aluno aluno5 = new Aluno("Rachel Green", "rachel_green@gmail.com",29);
        alunoRepository.save(aluno1);
        alunoRepository.save(aluno2);
        alunoRepository.save(aluno3);
        alunoRepository.save(aluno4);
        alunoRepository.save(aluno5);
        List<Avaliacao> listaAvaliacoesSalvas = new ArrayList<>();
        for(int i=1; i<=10; i++){
            Avaliacao a = new Avaliacao("Avaliação "+i);
            avaliacaoRepository.save(a);
            listaAvaliacoesSalvas.add(a);
        }

        RespostaQuestao resposta1 = new RespostaQuestao(listaAvaliacoesSalvas.get(0),aluno1, 3);
        RespostaQuestao resposta2 = new RespostaQuestao(listaAvaliacoesSalvas.get(1),aluno5, 7);
        RespostaQuestao resposta3 = new RespostaQuestao(listaAvaliacoesSalvas.get(2),aluno1, 10);
        RespostaQuestao resposta4 = new RespostaQuestao(listaAvaliacoesSalvas.get(3),aluno2, 3);
        RespostaQuestao resposta5 = new RespostaQuestao(listaAvaliacoesSalvas.get(4),aluno5, 7);
        RespostaQuestao resposta6 = new RespostaQuestao(listaAvaliacoesSalvas.get(5),aluno2, 8);
        RespostaQuestao resposta7 = new RespostaQuestao(listaAvaliacoesSalvas.get(6),aluno1, 4);
        RespostaQuestao resposta8 = new RespostaQuestao(listaAvaliacoesSalvas.get(7),aluno3, 3);
        RespostaQuestao resposta9 = new RespostaQuestao(listaAvaliacoesSalvas.get(8),aluno2, 1);
        RespostaQuestao resposta10 = new RespostaQuestao(listaAvaliacoesSalvas.get(9),aluno4, 2);
        RespostaQuestao resposta11 = new RespostaQuestao(listaAvaliacoesSalvas.get(1),aluno3, 9);
        RespostaQuestao resposta12 = new RespostaQuestao(listaAvaliacoesSalvas.get(2),aluno4, 4);

        respostaRepository.save(resposta1);respostaRepository.save(resposta5);respostaRepository.save(resposta9);// 1l 2l 3l
        respostaRepository.save(resposta2);respostaRepository.save(resposta6);respostaRepository.save(resposta10); // 4l 5l 6l
        respostaRepository.save(resposta3);respostaRepository.save(resposta7);respostaRepository.save(resposta11);// 7l 8l 9l
        respostaRepository.save(resposta4);respostaRepository.save(resposta8);respostaRepository.save(resposta12); // 10l 11l 12l

        /*
        List<RespostaQuestao> respostaQuestoes = respostaRepository.findAll();
        for (RespostaQuestao r:respostaQuestoes) {
            System.out.println("Resposta: " + r.getNota() + " -- " + r.getAluno().getNome());
        }
        */
    }

    @Test
    public void respostaExiste(){
        Optional<RespostaQuestao>  resposta = respostaRepository.findById(13L);
        Assertions.assertFalse(resposta.isPresent());
        Optional<RespostaQuestao>  resposta2 = respostaRepository.findById(3L);
        Assertions.assertTrue(resposta2.isPresent());
    }

    @Test
    public void respostaEhDoAluno(){
        Assertions.assertNotNull(respostaRepository.respostaEhDoAluno(3L, 2L));//3  É DO ALUNO 2
        Assertions.assertNull(respostaRepository.respostaEhDoAluno(3L, 1L));//3 NÃO É DO ALUNO 1
        Assertions.assertNull(respostaRepository.respostaEhDoAluno(10L, 1L)); //10L -> 4L
        Assertions.assertNotNull(respostaRepository.respostaEhDoAluno(10L, 2L));
    }

    @Test
    public void listarTodos(){
        List<RespostaQuestao> respostas = (List<RespostaQuestao>) respostaRepository.buscarTodasPorIdAluno(1L);
        Assertions.assertEquals(3, respostas.size());
    }

    @Test
    public void mediaNotaTodos(){
        List<Object[]> lista = respostaRepository.mediaNotaTodos();

        double mediaEsperada = 17.0/3.0;
        for (Object obj[] : lista) {
            Long idAluno = (Long) obj[0];
            Double mediaNotas = (double) obj[1];
            System.out.println("Aluno: " + idAluno + "| média: "+mediaNotas);
            if(idAluno == 1) { Assertions.assertEquals(mediaEsperada, mediaNotas);}
            if(idAluno == 2) { Assertions.assertEquals(4, mediaNotas);}
            if(idAluno == 3) { Assertions.assertEquals(6, mediaNotas);}
            if(idAluno == 4) { Assertions.assertEquals(3, mediaNotas);}
            if(idAluno == 5) { Assertions.assertEquals(7, mediaNotas);}
        }
    }

    @Test
    public void mediaNotasDoAluno(){
        Double mediaNota = respostaRepository.mediaNotaPorIdAluno(1L);
        double mediaEsperada = 17.0/3.0;
        Assertions.assertEquals(mediaEsperada, mediaNota);
    }
}