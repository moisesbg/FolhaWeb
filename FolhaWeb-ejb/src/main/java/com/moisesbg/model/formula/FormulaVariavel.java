package com.moisesbg.model.formula;

import com.moisesbg.model.calculo.DadosCalculo;

import java.math.BigDecimal;

public class FormulaVariavel implements IFormulaCalculoEvento {


    @Override
    public RetornoCalculoEvento calcularEvento(DadosCalculo dadosCalculo) {
        return new RetornoCalculoEvento(BigDecimal.ZERO,BigDecimal.ZERO);
    }
}
