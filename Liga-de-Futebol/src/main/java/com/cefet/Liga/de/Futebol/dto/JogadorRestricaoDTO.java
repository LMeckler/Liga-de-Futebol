package com.cefet.Liga.de.Futebol.dto;

import java.time.LocalDate;

import com.cefet.Liga.de.Futebol.entities.JogadorRestricao;

public class JogadorRestricaoDTO {
    private Long id;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private PessoaDTO jogador;

    public JogadorRestricaoDTO() {}
    public JogadorRestricaoDTO(JogadorRestricao restricao) {
        this.id = restricao.getId();
        this.descricao = restricao.getDescricao();
        this.dataInicio = restricao.getDataInicio();
        this.dataFim = restricao.getDataFim();
        if (restricao.getJogador() != null) {
            this.jogador = new PessoaDTO(restricao.getJogador());
        }
    }
    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public LocalDate getDataInicio() { return dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public PessoaDTO getJogador() { return jogador; }
}
