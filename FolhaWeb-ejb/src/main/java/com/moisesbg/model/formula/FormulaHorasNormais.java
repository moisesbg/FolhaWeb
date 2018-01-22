package com.moisesbg.model.formula;


import com.moisesbg.model.calculo.DadosCalculo;
import com.moisesbg.model.calculo.TipoBase;
import com.moisesbg.model.evento.Unidade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class FormulaHorasNormais implements IFormulaCalculoEvento {

    protected int dias;
    protected int horas;
    static final int DIAS_MES = 30;

    protected FormulaHorasNormais() {

    }

    public RetornoCalculoEvento calcularEvento(DadosCalculo dadosCalculo) {
        BigDecimal valorReferencia;
        BigDecimal valorCalculado;
        ajustarDias(dadosCalculo);
        ajustarHoras(dadosCalculo);

        valorCalculado = dadosCalculo.getFuncionario().getSalario()
                .multiply(new BigDecimal(horas))
                .divide(new BigDecimal(dadosCalculo.getFuncionario().getHorasMensais()), RoundingMode.HALF_EVEN);

        if(ehEventoMontadorBaseInss(TipoBase.INSS, dadosCalculo.getEventoCalcular())) {
            dadosCalculo.somarBaseINSS(valorCalculado);
        }

        if(ehEventoMontadorBaseIrrf(TipoBase.IRRF, dadosCalculo.getEventoCalcular())) {
            dadosCalculo.somarBaseIRRF(valorCalculado);
        }

        valorReferencia = new BigDecimal(dadosCalculo.getEventoCalcular().getUnidade() == Unidade.DIAS ? dias : horas);

        return new RetornoCalculoEvento(valorReferencia,valorCalculado);
    }

    private void ajustarDias(DadosCalculo dadosCalculo) {
        LocalDate dataAdmissao = dadosCalculo.getFuncionario().getDataAdmissao();
        if(dadosCalculo.getCompetencia().getMonth() == dataAdmissao.getMonth() &&
                dadosCalculo.getCompetencia().getYear() == dataAdmissao.getYear())
            dias = dataAdmissao.lengthOfMonth() - dataAdmissao.getDayOfMonth();
        else
            dias = DIAS_MES;

    }

    private void ajustarHoras(DadosCalculo dadosCalculo) {
        horas = dias * dadosCalculo.getFuncionario().getHorasMensais() / DIAS_MES;
    }

}
