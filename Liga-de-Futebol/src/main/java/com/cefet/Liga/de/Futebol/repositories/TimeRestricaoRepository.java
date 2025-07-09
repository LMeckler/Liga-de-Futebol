package com.cefet.Liga.de.Futebol.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.Liga.de.Futebol.entities.TimeRestricao;

public interface TimeRestricaoRepository extends JpaRepository<TimeRestricao, Long> {
    List<TimeRestricao> findByTimeId(Long timeId);
}
