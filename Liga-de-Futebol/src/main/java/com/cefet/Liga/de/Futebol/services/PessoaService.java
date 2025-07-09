package com.cefet.Liga.de.Futebol.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.Liga.de.Futebol.dto.PessoaDTO;
import com.cefet.Liga.de.Futebol.entities.Cidade;
import com.cefet.Liga.de.Futebol.entities.Pessoa;
import com.cefet.Liga.de.Futebol.repositories.CidadeRepository;
import com.cefet.Liga.de.Futebol.repositories.PessoaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private CidadeRepository cidadeRepository;

    // Buscar todos as pessoas
    public List<PessoaDTO> findAll() {
        List<Pessoa> listaPessoa = pessoaRepository.findAll();
        return listaPessoa.stream().map(PessoaDTO::new).toList();
    }

    // Buscar por ID
    public PessoaDTO findById(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + id));
        return new PessoaDTO(pessoa);
    }

    // Inserir Pessoa
    public PessoaDTO insert(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setCpf(pessoaDTO.getCpf());
        pessoa.setDataNascimento(pessoaDTO.getDataNascimento());
        if (pessoaDTO.getCidade() != null) {
            Cidade cidade = cidadeRepository.findById(pessoaDTO.getCidade().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cidade não encontrada com ID: " + pessoaDTO.getCidade().getId()));
            pessoa.setCidade(cidade);
        }
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        return new PessoaDTO(pessoaSalva);
    }

    // Atualizar Pessoa
    public PessoaDTO update(Long id, PessoaDTO novaPessoaDTO) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + id));
        pessoa.setNome(novaPessoaDTO.getNome());
        pessoa.setCpf(novaPessoaDTO.getCpf());
        pessoa.setDataNascimento(novaPessoaDTO.getDataNascimento());
        if (novaPessoaDTO.getCidade() != null) {
            Cidade cidade = cidadeRepository.findById(novaPessoaDTO.getCidade().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cidade não encontrada com ID: " + novaPessoaDTO.getCidade().getId()));
            pessoa.setCidade(cidade);
        } else {
            pessoa.setCidade(null);
        }
        Pessoa atualizada = pessoaRepository.save(pessoa);
        return new PessoaDTO(atualizada);
    }

    // Remover por ID
    public void delete(Long id) {
        if (!pessoaRepository.existsById(id)) {
            throw new EntityNotFoundException("Pessoa não encontrada com ID: " + id);
        }
        pessoaRepository.deleteById(id);
    }
}