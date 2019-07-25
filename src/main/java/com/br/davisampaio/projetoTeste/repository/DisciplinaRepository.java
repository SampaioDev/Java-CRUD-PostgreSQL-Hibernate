package com.br.davisampaio.projetoTeste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.davisampaio.projetoTeste.model.Disciplina;

@Repository
public interface DisciplinaRepository  extends JpaRepository<Disciplina, Long>{

}
