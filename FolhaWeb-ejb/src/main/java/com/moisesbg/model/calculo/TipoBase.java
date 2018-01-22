package com.moisesbg.model.calculo;

public enum TipoBase {
    INSS("1"),
    IRRF("2");

    private String value;

    private TipoBase(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
