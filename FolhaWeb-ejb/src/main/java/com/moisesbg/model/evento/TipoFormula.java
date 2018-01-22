package com.moisesbg.model.evento;

import com.moisesbg.model.formula.*;

public enum TipoFormula {
    HORAS_NORMAIS("1",FormulaHorasNormais.class),
    INSS("2", FormulaInss.class),
    IRRF("3", FormulaIrrf.class),
    VARIAVEIS("4", FormulaVariavel.class);

    private String value;

    private Class classe;

    private TipoFormula(String value, Class classe) {
        this.value = value;
        this.classe = classe;
    }

    public String getValue() {
        return this.value;
    }

    public Class getClasse() {
        return this.classe;
    }

}
