package com.cefet.Liga.de.Futebol.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.Liga.de.Futebol.dto.JogadorRestricaoDTO;
import com.cefet.Liga.de.Futebol.entities.JogadorRestricao;
import com.cefet.Liga.de.Futebol.entities.Pessoa;
import com.cefet.Liga.de.Futebol.repositories.JogadorRestricaoRepository;
import com.cefet.Liga.de.Futebol.repositories.PessoaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class JogadorRestricaoService {
    @Autowired
    private JogadorRestricaoRepository jogadorRestricaoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<JogadorRestricaoDTO> findAll() {
        return jogadorRestricaoRepository.findAll().stream().map(JogadorRestricaoDTO::new).collect(Collectors.toList());
    }

    public List<JogadorRestricaoDTO> findByJogadorId(Long jogadorId) {
        return jogadorRestricaoRepository.findByJogadorId(jogadorId).stream().map(JogadorRestricaoDTO::new).collect(Collectors.toList());
    }

    public JogadorRestricaoDTO findById(Long id) {
        JogadorRestricao restricao = jogadorRestricaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restrição não encontrada com ID: " + id));
        return new JogadorRestricaoDTO(restricao);
    }

    public JogadorRestricaoDTO insert(@Valid JogadorRestricaoDTO dto) {
        JogadorRestricao restricao = new JogadorRestricao();
        restricao.setDescricao(dto.getDescricao());
        restricao.setDataInicio(dto.getDataInicio());
        restricao.setDataFim(dto.getDataFim());
        if (dto.getJogador() != null) {
            Pessoa jogador = pessoaRepository.findById(dto.getJogador().getId())
                .orElseThrow(() -> new EntityNotFoundException("Jogador não encontrado com ID: " + dto.getJogador().getId()));
            restricao.setJogador(jogador);
        }
        JogadorRestricao salvo = jogadorRestricaoRepository.save(restricao);
        return new JogadorRestricaoDTO(salvo);
    }

    public JogadorRestricaoDTO update(Long id, @Valid JogadorRestricaoDTO dto) {
        JogadorRestricao restricao = jogadorRestricaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restrição não encontrada com ID: " + id));
        restricao.setDescricao(dto.getDescricao());
        restricao.setDataInicio(dto.getDataInicio());
        restricao.setDataFim(dto.getDataFim());
        if (dto.getJogador() != null) {
            Pessoa jogador = pessoaRepository.findById(dto.getJogador().getId())
                .orElseThrow(() -> new EntityNotFoundException("Jogador não encontrado com ID: " + dto.getJogador().getId()));
            restricao.setJogador(jogador);
        } else {
            restricao.setJogador(null);
        }
        JogadorRestricao atualizado = jogadorRestricaoRepository.save(restricao);
        return new JogadorRestricaoDTO(atualizado);
    }

    public void delete(Long id) {
        if (!jogadorRestricaoRepository.existsById(id)) {
            throw new EntityNotFoundException("Restrição não encontrada com ID: " + id);
        }
        jogadorRestricaoRepository.deleteById(id);
    }
}
