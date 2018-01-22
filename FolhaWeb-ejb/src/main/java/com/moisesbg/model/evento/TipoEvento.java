package com.moisesbg.model.evento;

public enum TipoEvento {
    DESCONTO("D"),
    PROVENTO("P");

    private String value;

    TipoEvento(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
