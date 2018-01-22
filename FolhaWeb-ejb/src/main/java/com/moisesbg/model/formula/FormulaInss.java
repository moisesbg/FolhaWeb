package com.moisesbg.model.formula;

import com.moisesbg.model.calculo.DadosCalculo;
import com.moisesbg.model.calculo.TipoBase;

import java.math.BigDecimal;

public class FormulaInss implements IFormulaCalculoEvento {

    static final BigDecimal CEM_PORCENTO = new BigDecimal(100);

    private TabelaInss tabelaInss;

    protected FormulaInss() {
        tabelaInss = TabelaInss.get();
    }

    @Override
    public RetornoCalculoEvento calcularEvento(DadosCalculo dadosCalculo) {
        BigDecimal valorReferencia = tabelaInss.getFaixa(dadosCalculo.getBaseINSS()).getAliquota();
        BigDecimal valorCalculado = getBaseEfetivaINSS(dadosCalculo).multiply(valorReferencia);

        if(ehEventoMontadorBaseIrrf(TipoBase.IRRF, dadosCalculo.getEventoCalcular())) {
            dadosCalculo.diminuirBaseIRRF(valorCalculado);
        }

        return new RetornoCalculoEvento(valorReferencia.multiply(CEM_PORCENTO),valorCalculado);
    }

    private BigDecimal getBaseEfetivaINSS(DadosCalculo dadosCalculo){
        if(dadosCalculo.getBaseINSS().compareTo(BigDecimal.ZERO) == -1) {
            return BigDecimal.ZERO;
        }

        if(dadosCalculo.getBaseINSS().compareTo(tabelaInss.getTetoInss()) == 1){
            return tabelaInss.getTetoInss();
        }

        return dadosCalculo.getBaseINSS();
    }
}
