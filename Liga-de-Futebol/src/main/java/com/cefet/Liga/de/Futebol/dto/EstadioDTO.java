package com.cefet.Liga.de.Futebol.dto;

import com.cefet.Liga.de.Futebol.entities.Estadio;

public class EstadioDTO {
    private Long id;
    private String nome;
    private Integer capacidade;
    private CidadeDTO cidade;

    public EstadioDTO() {}
    public EstadioDTO(Estadio estadio) {
        this.id = estadio.getId();
        this.nome = estadio.getNome();
        this.capacidade = estadio.getCapacidade();
        if (estadio.getCidade() != null) {
            this.cidade = new CidadeDTO(estadio.getCidade());
        }
    }
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Integer getCapacidade() { return capacidade; }
    public CidadeDTO getCidade() { return cidade; }
}
