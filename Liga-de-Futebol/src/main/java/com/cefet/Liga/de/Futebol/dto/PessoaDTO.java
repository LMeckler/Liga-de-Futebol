package com.cefet.Liga.de.Futebol.dto;

import java.time.LocalDate;

import com.cefet.Liga.de.Futebol.entities.Pessoa;

public class PessoaDTO {
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private CidadeDTO cidade;

    public PessoaDTO() {}

    public PessoaDTO(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.cpf = pessoa.getCpf();
        this.dataNascimento = pessoa.getDataNascimento();
        if (pessoa.getCidade() != null) {
            this.cidade = new CidadeDTO(pessoa.getCidade());
        }
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public CidadeDTO getCidade() { return cidade; }
}