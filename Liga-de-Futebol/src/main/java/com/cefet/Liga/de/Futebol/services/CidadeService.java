package com.cefet.Liga.de.Futebol.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.Liga.de.Futebol.dto.CidadeDTO;
import com.cefet.Liga.de.Futebol.entities.Cidade;
import com.cefet.Liga.de.Futebol.repositories.CidadeRepository;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;

    @Transactional(readOnly = true)
    public List<CidadeDTO> listar() {
        return cidadeRepository.findAll().stream().map(CidadeDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<CidadeDTO> buscar(Long id) {
        return cidadeRepository.findById(id).map(CidadeDTO::new);
    }

    @Transactional
    public CidadeDTO criar(Cidade cidade) {
        return new CidadeDTO(cidadeRepository.save(cidade));
    }

    @Transactional
    public Optional<CidadeDTO> atualizar(Long id, Cidade cidade) {
        return cidadeRepository.findById(id).map(c -> {
            cidade.setId(id);
            return new CidadeDTO(cidadeRepository.save(cidade));
        });
    }

    @Transactional
    public boolean deletar(Long id) {
        if (!cidadeRepository.existsById(id)) return false;
        cidadeRepository.deleteById(id);
        return true;
    }
}
