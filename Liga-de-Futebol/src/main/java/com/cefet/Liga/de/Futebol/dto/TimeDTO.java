package com.cefet.Liga.de.Futebol.dto;

import com.cefet.Liga.de.Futebol.entities.Time;

public class TimeDTO {
    private Long id;
    private String nome;
    private CidadeDTO cidade;
    private EstadioDTO estadio;

    public TimeDTO() {}
    public TimeDTO(Time time) {
        this.id = time.getId();
        this.nome = time.getNome();
        if (time.getCidade() != null) {
            this.cidade = new CidadeDTO(time.getCidade());
        }
        if (time.getEstadio() != null) {
            this.estadio = new EstadioDTO(time.getEstadio());
        }
    }
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public CidadeDTO getCidade() { return cidade; }
    public EstadioDTO getEstadio() { return estadio; }
}
