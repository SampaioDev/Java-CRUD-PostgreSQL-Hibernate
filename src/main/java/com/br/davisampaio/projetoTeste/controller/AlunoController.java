package com.br.davisampaio.projetoTeste.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.davisampaio.projetoTeste.exception.ResourceNotFoundException;
import com.br.davisampaio.projetoTeste.model.Aluno;
import com.br.davisampaio.projetoTeste.repository.AlunoRepository;

@RestController
@RequestMapping("/api/v1")
public class AlunoController {

	@Autowired
    private AlunoRepository alunoRepository;	
	
	@GetMapping("/alunos")
    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }
	
	@GetMapping("/alunos/{id}")
    public ResponseEntity<Aluno> getEmployeeById(@PathVariable(value = "id") Long alunoId)
        throws ResourceNotFoundException {
        Aluno aluno = alunoRepository.findById(alunoId)
          .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + alunoId));
        return ResponseEntity.ok().body(aluno);
    }
	
	@PostMapping("/alunos")
    public Aluno createEmployee(@Valid @RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }
	
	@PutMapping("/alunos/{id}")
    public ResponseEntity<Aluno> updateEmployee(@PathVariable(value = "id") Long alunoId,
         @Valid @RequestBody Aluno alunoDetails) throws ResourceNotFoundException {
        Aluno aluno= alunoRepository.findById(alunoId)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + alunoId));

        aluno.setEmail(alunoDetails.getEmail());
        aluno.setNome(alunoDetails.getNome());
        aluno.setIdade(alunoDetails.getIdade());
        aluno.setVp1(alunoDetails.getVp1());
        aluno.setVp2(alunoDetails.getVp2());
        aluno.setVf(alunoDetails.getVf());
        aluno.setMedia(alunoDetails.getMedia());
        final Aluno updatedAluno = alunoRepository.save(aluno);
        return ResponseEntity.ok(updatedAluno);
    }
	
	@DeleteMapping("/alunos/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long alunoId)
         throws ResourceNotFoundException {
        Aluno aluno = alunoRepository.findById(alunoId)
       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + alunoId));

        alunoRepository.delete(aluno);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	
}
