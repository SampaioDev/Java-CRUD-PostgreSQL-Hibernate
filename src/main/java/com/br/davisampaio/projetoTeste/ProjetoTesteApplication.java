package com.br.davisampaio.projetoTeste;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.davisampaio.projetoTeste.controller.DisciplinaController;
import com.br.davisampaio.projetoTeste.controller.ProfessorController;
import com.br.davisampaio.projetoTeste.exception.ResourceNotFoundException;
import com.br.davisampaio.projetoTeste.model.Disciplina;
import com.br.davisampaio.projetoTeste.model.Professor;

@SpringBootApplication
public class ProjetoTesteApplication {
	
	@Autowired
	public static DisciplinaController controller;
	
	@Autowired
	public static ProfessorController pcontroller;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoTesteApplication.class, args);
		
		List<Disciplina> disciplinas = controller.getAllDisciplinas();

		Professor professor = new Professor();
		List<Professor> professores = pcontroller.getAllProfessors();
		
		if(!professores.isEmpty() && professores != null) {
			professor = professores.get(0);
			
			for (Disciplina d : disciplinas) {
				professor.getDisciplinas().add(d);
			}
			
			try {
				pcontroller.updateProfessor(professor.getId(), professor);
			} catch (ResourceNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
