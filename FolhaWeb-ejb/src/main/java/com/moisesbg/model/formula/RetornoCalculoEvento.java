package com.moisesbg.model.formula;

import java.math.BigDecimal;

public class RetornoCalculoEvento {

    private BigDecimal valorReferencia;
    private BigDecimal valorCalculado;


    public RetornoCalculoEvento(BigDecimal valorReferencia, BigDecimal valorCalculado) {
        this.valorReferencia = valorReferencia;
        this.valorCalculado = valorCalculado;
    }


    public BigDecimal getValorReferencia() {
        return valorReferencia;
    }


    public BigDecimal getValorCalculado() {
        return valorCalculado;
    }

}
