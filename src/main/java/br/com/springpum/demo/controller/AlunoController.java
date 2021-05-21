package br.com.springpum.demo.controller;


import java.net.URI;
import java.util.List;
import java.util.Optional;


import br.com.springpum.demo.dto.AlunoDTO;
import br.com.springpum.demo.form.AlunoForm;
import br.com.springpum.demo.model.Aluno;
import br.com.springpum.demo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
// esse controller é um restController, desta forma n precisamos colocar o @ResponseBody
// ele ja assume isso automaticcmente em cada método
@RequestMapping(value = "/alunos")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;


    @PostMapping
    @Transactional
    public ResponseEntity<AlunoDTO> cadastrar(@RequestBody @Valid AlunoForm form, UriComponentsBuilder uriBuilder) {

        Aluno aluno = form.converter();
        alunoRepository.save(aluno);

        URI uri =  uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(new AlunoDTO(aluno));
    }


    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> consultar(@PathVariable("id") Long idTopico){
        //primeiro verificar se existe um tópico com este ID
        Optional<Aluno> op = alunoRepository.findById(idTopico);
        if(op.isPresent()){
            return ResponseEntity.ok(new AlunoDTO(op.get()));
        }
        return ResponseEntity.notFound().build();
    }



}
