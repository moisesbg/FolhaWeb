package com.moisesbg.model.calculo.execucao;


import com.moisesbg.model.calculo.DadosCalculo;
import com.moisesbg.model.evento.Evento;
import com.moisesbg.model.formula.FabricaFormula;
import com.moisesbg.model.formula.IFormulaCalculoEvento;
import com.moisesbg.model.formula.RetornoCalculoEvento;

class CalcularEvento {
    RetornoCalculoEvento executar(DadosCalculo dadosCalculo) throws IllegalAccessException, InstantiationException,
            ClassNotFoundException {
        Evento evento = dadosCalculo.getEventoCalcular();
        IFormulaCalculoEvento formula = FabricaFormula.getFormula(evento.getTipoFormula());
        return formula.calcularEvento(dadosCalculo);
    }
}
