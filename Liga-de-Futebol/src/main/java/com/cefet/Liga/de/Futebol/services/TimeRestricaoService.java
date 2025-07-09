package com.cefet.Liga.de.Futebol.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.Liga.de.Futebol.dto.TimeRestricaoDTO;
import com.cefet.Liga.de.Futebol.entities.Time;
import com.cefet.Liga.de.Futebol.entities.TimeRestricao;
import com.cefet.Liga.de.Futebol.repositories.TimeRepository;
import com.cefet.Liga.de.Futebol.repositories.TimeRestricaoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class TimeRestricaoService {
    @Autowired
    private TimeRestricaoRepository timeRestricaoRepository;
    @Autowired
    private TimeRepository timeRepository;

    public List<TimeRestricaoDTO> findAll() {
        return timeRestricaoRepository.findAll().stream().map(TimeRestricaoDTO::new).collect(Collectors.toList());
    }

    public List<TimeRestricaoDTO> findByTimeId(Long timeId) {
        return timeRestricaoRepository.findByTimeId(timeId).stream().map(TimeRestricaoDTO::new).collect(Collectors.toList());
    }

    public TimeRestricaoDTO findById(Long id) {
        TimeRestricao restricao = timeRestricaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restrição não encontrada com ID: " + id));
        return new TimeRestricaoDTO(restricao);
    }

    public TimeRestricaoDTO insert(@Valid TimeRestricaoDTO dto) {
        TimeRestricao restricao = new TimeRestricao();
        restricao.setDescricao(dto.getDescricao());
        restricao.setDataInicio(dto.getDataInicio());
        restricao.setDataFim(dto.getDataFim());
        if (dto.getTime() != null) {
            Time time = timeRepository.findById(dto.getTime().getId())
                .orElseThrow(() -> new EntityNotFoundException("Time não encontrado com ID: " + dto.getTime().getId()));
            restricao.setTime(time);
        }
        TimeRestricao salvo = timeRestricaoRepository.save(restricao);
        return new TimeRestricaoDTO(salvo);
    }

    public TimeRestricaoDTO update(Long id, @Valid TimeRestricaoDTO dto) {
        TimeRestricao restricao = timeRestricaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restrição não encontrada com ID: " + id));
        restricao.setDescricao(dto.getDescricao());
        restricao.setDataInicio(dto.getDataInicio());
        restricao.setDataFim(dto.getDataFim());
        if (dto.getTime() != null) {
            Time time = timeRepository.findById(dto.getTime().getId())
                .orElseThrow(() -> new EntityNotFoundException("Time não encontrado com ID: " + dto.getTime().getId()));
            restricao.setTime(time);
        } else {
            restricao.setTime(null);
        }
        TimeRestricao atualizado = timeRestricaoRepository.save(restricao);
        return new TimeRestricaoDTO(atualizado);
    }

    public void delete(Long id) {
        if (!timeRestricaoRepository.existsById(id)) {
            throw new EntityNotFoundException("Restrição não encontrada com ID: " + id);
        }
        timeRestricaoRepository.deleteById(id);
    }
}
