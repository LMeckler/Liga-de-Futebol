package com.cefet.Liga.de.Futebol.dto;

import com.cefet.Liga.de.Futebol.entities.Cidade;

public class CidadeDTO {
    private Long id;
    private String nome;
    private String estado;

    public CidadeDTO() {}
    public CidadeDTO(Cidade cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
        this.estado = cidade.getEstado();
    }
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEstado() { return estado; }
}
