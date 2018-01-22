package com.moisesbg.model.formula;

import com.moisesbg.model.calculo.DadosCalculo;

import java.math.BigDecimal;

public class FormulaIrrf implements IFormulaCalculoEvento {

    static final BigDecimal CEM_PORCENTO = new BigDecimal(100);

    private TabelaIrrf tabelaIrrf;

    protected FormulaIrrf() {
        tabelaIrrf = TabelaIrrf.get();
    }


    @Override
    public RetornoCalculoEvento calcularEvento(DadosCalculo dadosCalculo) {
        BigDecimal base = getBaseEfetivaIRRF(dadosCalculo);

        BigDecimal valorReferencia = tabelaIrrf.getFaixa(base).getAliquota();
        BigDecimal valorCalculado = base.multiply(valorReferencia).subtract(tabelaIrrf.getFaixa(base).getDesconto());

        return new RetornoCalculoEvento(valorReferencia.multiply(CEM_PORCENTO),valorCalculado);
    }

    private BigDecimal getBaseEfetivaIRRF(DadosCalculo dadosCalculo){
        BigDecimal descontoDependentes = tabelaIrrf.getDescontoDependente()
                .multiply(new BigDecimal(dadosCalculo.getFuncionario().getQtdDependentes()));

        BigDecimal base = dadosCalculo.getBaseIRRF()
                .subtract(descontoDependentes);

        if(base.compareTo(BigDecimal.ZERO) == -1) {
            return BigDecimal.ZERO;
        }

        return base;
    }
}
