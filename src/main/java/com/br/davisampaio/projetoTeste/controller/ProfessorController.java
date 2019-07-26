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
import com.br.davisampaio.projetoTeste.model.Professor;
import com.br.davisampaio.projetoTeste.repository.DisciplinaRepository;
import com.br.davisampaio.projetoTeste.repository.ProfessorRepository;

@RestController
@RequestMapping("/api/v1")
public class ProfessorController {

	@Autowired
    private ProfessorRepository professorRepository;	
	@Autowired
    private DisciplinaRepository disciplinaRepository;	
	
	@GetMapping("/professor")
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }
	
	@GetMapping("/professor/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable(value = "id") Long professorId)
        throws ResourceNotFoundException {
        Professor professor = professorRepository.findById(professorId)
          .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado para este id :: " + professorId));
        return ResponseEntity.ok().body(professor);
    }
	
	@PostMapping("/professor")
    public Professor createProfessor(@Valid @RequestBody Professor professor) {
        return professorRepository.save(professor);
    }
	
	@PostMapping("/professor/{disciplinaIds}")
    public Professor createProfessorWithDisciplina(@Valid @RequestBody Professor professor, @PathVariable(value = "disciplinaIds") List<Long> disciplinaIds) throws ResourceNotFoundException {
    	List<Disciplina> disciplinas = disciplinaRepository.findAllById(disciplinaIds);
    	if(!disciplinas.isEmpty() && disciplinas != null) {
    		professor.setDisciplinas(disciplinas);    		
    	}
		
		return professorRepository.save(professor);
    }
	
	@PutMapping("/professor/professorId:{id}/disciplinaId:{idDisciplina}")
    public Professor addDisciplinaToProfessor(@PathVariable(value = "id") Long professorId, @PathVariable(value = "idDisciplina") Long disciplinaId) throws ResourceNotFoundException {
		Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
				.orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada para este id :: " + disciplinaId));;
		Professor professor = professorRepository.findById(professorId)
		          .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado para este id :: " + professorId));

		professor.getDisciplinas().add(disciplina);
		
        return professorRepository.save(professor);
    }
	
	@PutMapping("/professor/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable(value = "id") Long professorId,
         @Valid @RequestBody Professor professorDetails) throws ResourceNotFoundException {
        Professor professor= professorRepository.findById(professorId)
        .orElseThrow(() -> new ResourceNotFoundException("não encontrado para este id :: " + professorId));

        professor.setEmail(professorDetails.getEmail());
        professor.setNome(professorDetails.getNome());
        professor.setIdade(professorDetails.getIdade());
        final Professor updatedProfessor = professorRepository.save(professor);
        return ResponseEntity.ok(updatedProfessor);
    }
	
	@DeleteMapping("/professor/{id}")
    public Map<String, Boolean> deleteProfessor(@PathVariable(value = "id") Long professorId)
         throws ResourceNotFoundException {
        Professor professor = professorRepository.findById(professorId)
       .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado para este id :: " + professorId));

        professorRepository.delete(professor);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	
}
