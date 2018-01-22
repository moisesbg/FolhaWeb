package com.moisesbg.model;

public enum SimNao {
    SIM("S"),
    NAO("N");

    private String value;

    SimNao(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value;
    }
}