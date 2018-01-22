package com.moisesbg.model.calculo.folha;


import com.moisesbg.model.Entidade;
import com.moisesbg.model.evento.Evento;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_folha")
@SequenceGenerator(name = "seq_itens_folha", sequenceName = "seq_itens_folha", allocationSize = 1)
public class ItemFolha implements Entidade{

    @Id
    @GeneratedValue(generator = "seq_itens_folha", strategy = GenerationType.SEQUENCE)
    public Integer id;

    @NotNull(message = "Não é possível gerar um item na folha de pagamento, sem informar o evento a que se refere este item")
    @JoinColumn(name = "i_eventos")
    @ManyToOne(optional = false)
    private Evento evento;

    @Column(name = "vlr_referencia")
    @NotNull(message = "Não é possível gerar um item na folha de pagamento sem informar o valor de referência")
    @Min(value = 0, message = "O valor de referência de im item da folha de pagamento não pode ser menor do que zero")
    private BigDecimal valorReferencia;

    @Column(name = "vlr_calculado")
    @NotNull(message = "Não é possível gerar um item na folha de pagamento sem informar o valor calculado")
    @DecimalMin(value = "0.01", message = "Não é possível gerar um item na folha de pagamento com valor inferior a {value}")
    private BigDecimal valorCalculado;

    @NotNull(message = "Um item de folha de pagamento deve estar relacionalido com uma folha")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.PERSIST}, optional = false)
    @JoinColumn(name = "i_folhas_pagamentos")
    private Folha folha;

    public ItemFolha() {

    }

    public ItemFolha(Builder builder) {
        this.id = builder.id;
        this.evento = builder.evento;
        this.valorReferencia = builder.valorReferencia;
        this.valorCalculado = builder.valorCalculado;
        this.folha = builder.folha;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public Evento getEvento() {
        return evento;
    }

    public BigDecimal getValorReferencia() {
        return valorReferencia;
    }

    public BigDecimal getValorCalculado() {
        return valorCalculado;
    }

    public void setFolha(Folha folha) {
        this.folha = folha;
    }

    public static class Builder {
        private Integer id;
        private Evento evento;
        private BigDecimal valorReferencia;
        private BigDecimal valorCalculado;
        private Folha folha;

        public ItemFolha build() {
            return new ItemFolha(this);
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder evento(Evento evento) {
            this.evento = evento;
            return this;
        }

        public Builder valorReferencia(BigDecimal valorReferencia) {
            this.valorReferencia = valorReferencia;
            return this;
        }

        public Builder valorCalculado(BigDecimal valorCalculado) {
            this.valorCalculado = valorCalculado;
            return this;
        }

        public Builder Folha(Folha folha) {
            this.folha = folha;
            return this;
        }
    }
}
