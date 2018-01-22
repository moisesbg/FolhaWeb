package com.moisesbg.model.formula;

import com.moisesbg.model.SimNao;
import com.moisesbg.model.calculo.DadosCalculo;
import com.moisesbg.model.calculo.TipoBase;
import com.moisesbg.model.evento.Evento;

public interface IFormulaCalculoEvento {

    public RetornoCalculoEvento calcularEvento(DadosCalculo dadosCalculo);

    default boolean ehEventoMontadorBaseInss(TipoBase tipoBase, Evento evento) {
        if(TipoBase.INSS.equals(tipoBase) && SimNao.SIM.equals(evento.getMontaBaseInss())){
            return true;
        }
        return false;
    }

    default boolean ehEventoMontadorBaseIrrf(TipoBase tipoBase, Evento evento) {
        if(TipoBase.IRRF.equals(tipoBase) && SimNao.SIM.equals(evento.getMontaBaseIrrf())){
            return true;
        }
        return false;
    }
}
