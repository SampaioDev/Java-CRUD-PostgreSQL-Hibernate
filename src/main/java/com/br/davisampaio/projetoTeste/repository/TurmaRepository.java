package com.br.davisampaio.projetoTeste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.davisampaio.projetoTeste.model.Turma;

@Repository
public interface TurmaRepository  extends JpaRepository<Turma, Long>{

}
