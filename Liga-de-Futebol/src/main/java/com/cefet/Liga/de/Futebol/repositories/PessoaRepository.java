package com.cefet.Liga.de.Futebol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.Liga.de.Futebol.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {   
}