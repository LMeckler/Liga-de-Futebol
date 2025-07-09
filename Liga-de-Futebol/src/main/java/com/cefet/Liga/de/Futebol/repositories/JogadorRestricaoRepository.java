package com.cefet.Liga.de.Futebol.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.Liga.de.Futebol.entities.JogadorRestricao;

public interface JogadorRestricaoRepository extends JpaRepository<JogadorRestricao, Long> {
    List<JogadorRestricao> findByJogadorId(Long jogadorId);
}
