package com.cefet.Liga.de.Futebol.dto;

import java.time.LocalDate;

import com.cefet.Liga.de.Futebol.entities.TimeRestricao;

public class TimeRestricaoDTO {
    private Long id;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private TimeDTO time;

    public TimeRestricaoDTO() {}
    public TimeRestricaoDTO(TimeRestricao restricao) {
        this.id = restricao.getId();
        this.descricao = restricao.getDescricao();
        this.dataInicio = restricao.getDataInicio();
        this.dataFim = restricao.getDataFim();
        if (restricao.getTime() != null) {
            this.time = new TimeDTO(restricao.getTime());
        }
    }
    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public LocalDate getDataInicio() { return dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public TimeDTO getTime() { return time; }
}
