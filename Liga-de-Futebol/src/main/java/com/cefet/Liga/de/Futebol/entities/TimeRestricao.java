package com.cefet.Liga.de.Futebol.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_Time_Restricao")
public class TimeRestricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDate data_Inicio;

    @Column(nullable = false)
    private LocalDate data_Fim;

    @ManyToOne
    @JoinColumn(name = "id_Time")
    private Time time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return data_Inicio;
    }

    public void setDataInicio(LocalDate data_Inicio) {
        this.data_Inicio = data_Inicio;
    }

    public LocalDate getDataFim() {
        return data_Fim;
    }

    public void setDataFim(LocalDate data_Fim) {
        this.data_Fim = data_Fim;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((data_Inicio == null) ? 0 : data_Inicio.hashCode());
        result = prime * result + ((data_Fim == null) ? 0 : data_Fim.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TimeRestricao other = (TimeRestricao) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        if (data_Inicio == null) {
            if (other.data_Inicio != null)
                return false;
        } else if (!data_Inicio.equals(other.data_Inicio))
            return false;
        if (data_Fim == null) {
            if (other.data_Fim != null)
                return false;
        } else if (!data_Fim.equals(other.data_Fim))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        return true;
    }

    
}
