package com.moisesbg.model.formula;


import com.moisesbg.model.evento.TipoFormula;

public class FabricaFormula {

    public static IFormulaCalculoEvento getFormula(TipoFormula tipo) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        return (IFormulaCalculoEvento) tipo.getClasse().newInstance();
    }

}
