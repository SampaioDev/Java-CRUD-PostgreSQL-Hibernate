package com.br.davisampaio.projetoTeste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.davisampaio.projetoTeste.model.Aluno;

@Repository
public interface AlunoRepository  extends JpaRepository<Aluno, Long>{

}
