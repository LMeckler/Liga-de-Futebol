package com.cefet.Liga.de.Futebol.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.Liga.de.Futebol.dto.ContratoDTO;
import com.cefet.Liga.de.Futebol.entities.Contrato;
import com.cefet.Liga.de.Futebol.entities.Pessoa;
import com.cefet.Liga.de.Futebol.entities.Time;
import com.cefet.Liga.de.Futebol.repositories.ContratoRepository;
import com.cefet.Liga.de.Futebol.repositories.PessoaRepository;
import com.cefet.Liga.de.Futebol.repositories.TimeRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class ContratoService {
    @Autowired
    private ContratoRepository contratoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private TimeRepository timeRepository;

    public List<ContratoDTO> findAll() {
        return contratoRepository.findAll().stream().map(ContratoDTO::new).collect(Collectors.toList());
    }

    public ContratoDTO findById(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrato não encontrado com ID: " + id));
        return new ContratoDTO(contrato);
    }

    public ContratoDTO insert(@Valid ContratoDTO dto) {
        Contrato contrato = new Contrato();
        contrato.setDataInicio(dto.getDataInicio());
        contrato.setDataFim(dto.getDataFim());
        contrato.setTipo(dto.getTipo());
        contrato.setFuncao(dto.getFuncao());
        contrato.setSituacao(dto.getSituacao());
        contrato.setObservacao(dto.getObservacao());
        if (dto.getTime() != null) {
            Time time = timeRepository.findById(dto.getTime().getId())
                .orElseThrow(() -> new EntityNotFoundException("Time não encontrado com ID: " + dto.getTime().getId()));
            contrato.setTime(time);
        }
        if (dto.getPessoa() != null) {
            Pessoa pessoa = pessoaRepository.findById(dto.getPessoa().getId())
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + dto.getPessoa().getId()));
            contrato.setPessoa(pessoa);
        }
        Contrato salvo = contratoRepository.save(contrato);
        return new ContratoDTO(salvo);
    }

    public ContratoDTO update(Long id, @Valid ContratoDTO dto) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrato não encontrado com ID: " + id));
        contrato.setDataInicio(dto.getDataInicio());
        contrato.setDataFim(dto.getDataFim());
        contrato.setTipo(dto.getTipo());
        contrato.setFuncao(dto.getFuncao());
        contrato.setSituacao(dto.getSituacao());
        contrato.setObservacao(dto.getObservacao());
        if (dto.getTime() != null) {
            Time time = timeRepository.findById(dto.getTime().getId())
                .orElseThrow(() -> new EntityNotFoundException("Time não encontrado com ID: " + dto.getTime().getId()));
            contrato.setTime(time);
        } else {
            contrato.setTime(null);
        }
        if (dto.getPessoa() != null) {
            Pessoa pessoa = pessoaRepository.findById(dto.getPessoa().getId())
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + dto.getPessoa().getId()));
            contrato.setPessoa(pessoa);
        } else {
            contrato.setPessoa(null);
        }
        Contrato atualizado = contratoRepository.save(contrato);
        return new ContratoDTO(atualizado);
    }

    public void delete(Long id) {
        if (!contratoRepository.existsById(id)) {
            throw new EntityNotFoundException("Contrato não encontrado com ID: " + id);
        }
        contratoRepository.deleteById(id);
    }
}
