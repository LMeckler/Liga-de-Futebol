package com.cefet.Liga.de.Futebol.dto;

import java.time.LocalDate;

import com.cefet.Liga.de.Futebol.entities.Contrato;

public class ContratoDTO {
    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String tipo;
    private String funcao;
    private String situacao;
    private String observacao;
    private TimeDTO time;
    private PessoaDTO pessoa;

    public ContratoDTO() {}
    public ContratoDTO(Contrato contrato) {
        this.id = contrato.getId();
        this.dataInicio = contrato.getDataInicio();
        this.dataFim = contrato.getDataFim();
        this.tipo = contrato.getTipo();
        this.funcao = contrato.getFuncao();
        this.situacao = contrato.getSituacao();
        this.observacao = contrato.getObservacao();
        if (contrato.getTime() != null) {
            this.time = new TimeDTO(contrato.getTime());
        }
        if (contrato.getPessoa() != null) {
            this.pessoa = new PessoaDTO(contrato.getPessoa());
        }
    }
    public Long getId() { return id; }
    public LocalDate getDataInicio() { return dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public String getTipo() { return tipo; }
    public String getFuncao() { return funcao; }
    public String getSituacao() { return situacao; }
    public String getObservacao() { return observacao; }
    public TimeDTO getTime() { return time; }
    public PessoaDTO getPessoa() { return pessoa; }
}
