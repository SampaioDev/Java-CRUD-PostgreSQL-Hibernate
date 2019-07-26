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
import com.br.davisampaio.projetoTeste.model.Turma;
import com.br.davisampaio.projetoTeste.repository.TurmaRepository;

@RestController
@RequestMapping("/api/v1")
public class TurmaController {

	@Autowired
    private TurmaRepository turmaRepository;	
	
	@GetMapping("/turmas")
    public List<Turma> getAllTurmas() {
        return turmaRepository.findAll();
    }
	
	@GetMapping("/turmas/{id}")
    public ResponseEntity<Turma> getTurmaById(@PathVariable(value = "id") Long turmaId)
        throws ResourceNotFoundException {
        Turma turma = turmaRepository.findById(turmaId)
          .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada para este id :: " + turmaId));
        return ResponseEntity.ok().body(turma);
    }
	
	@PostMapping("/turmas")
    public Turma createTurma(@Valid @RequestBody Turma turma) {
        return turmaRepository.save(turma);
    }
	
	@PutMapping("/turmas/{id}")
    public ResponseEntity<Turma> updateTurma(@PathVariable(value = "id") Long turmaId,
         @Valid @RequestBody Turma turmaDetails) throws ResourceNotFoundException {
        Turma turma= turmaRepository.findById(turmaId)
        .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada para este id :: " + turmaId));

        final Turma updatedTurma = turmaRepository.save(turma);
        return ResponseEntity.ok(updatedTurma);
    }
	
	@DeleteMapping("/turmas/{id}")
    public Map<String, Boolean> deleteTurma(@PathVariable(value = "id") Long turmaId)
         throws ResourceNotFoundException {
        Turma turma = turmaRepository.findById(turmaId)
       .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada para este id :: " + turmaId));

        turmaRepository.delete(turma);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	
}
