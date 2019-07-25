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
import com.br.davisampaio.projetoTeste.model.Disciplina;
import com.br.davisampaio.projetoTeste.repository.DisciplinaRepository;

@RestController
@RequestMapping("/api/v1")
public class DisciplinaController {

	@Autowired
    private DisciplinaRepository disciplinaRepository;	
	
	@GetMapping("/disciplinas")
    public List<Disciplina> getAllDisciplinas() {
        return disciplinaRepository.findAll();
    }
	
	@GetMapping("/disciplinas/{id}")
    public ResponseEntity<Disciplina> getDisciplinaById(@PathVariable(value = "id") Long disciplinaId)
        throws ResourceNotFoundException {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
          .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada para este id :: " + disciplinaId));
        return ResponseEntity.ok().body(disciplina);
    }
	
	@PostMapping("/disciplinas")
    public Disciplina createDisciplina(@Valid @RequestBody Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }
	
	@PutMapping("/disciplinas/{id}")
    public ResponseEntity<Disciplina> updateDisciplina(@PathVariable(value = "id") Long disciplinaId,
         @Valid @RequestBody Disciplina disciplinaDetails) throws ResourceNotFoundException {
        Disciplina disciplina= disciplinaRepository.findById(disciplinaId)
        .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada para este id :: " + disciplinaId));

        disciplina.setNome(disciplinaDetails.getNome());
        disciplina.setDescricao(disciplinaDetails.getDescricao());
        final Disciplina updatedDisciplina = disciplinaRepository.save(disciplina);
        return ResponseEntity.ok(updatedDisciplina);
    }
	
	@DeleteMapping("/disciplinas/{id}")
    public Map<String, Boolean> deleteDisciplina(@PathVariable(value = "id") Long disciplinaId)
         throws ResourceNotFoundException {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
       .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada para este id :: " + disciplinaId));

        disciplinaRepository.delete(disciplina);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	
}
