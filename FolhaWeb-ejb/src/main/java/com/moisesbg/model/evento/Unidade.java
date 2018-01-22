package com.moisesbg.model.evento;

public enum Unidade {
    AUTOMATICO("A"),
    HORAS("H"),
    DIAS("D");

    private String value;

    Unidade(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
