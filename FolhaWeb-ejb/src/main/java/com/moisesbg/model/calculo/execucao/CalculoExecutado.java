package com.moisesbg.model.calculo.execucao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CalculoExecutado {

    private List<String> erros;

    private List<ItemFolhaCalculada> itensFolha;

    public CalculoExecutado() {
        resetar();
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
        this.getItensFolha().clear();
    }

    public List<ItemFolhaCalculada> getItensFolha() {
        return itensFolha;
    }

    public void setItensFolha(List<ItemFolhaCalculada> itensFolha) {
        this.itensFolha = itensFolha;
    }

    public boolean deuProblemaNoCalculo() {
        return Objects.nonNull(erros) && erros.size() > 0;
    }

    public void resetar() {
        erros = new ArrayList<>();
        itensFolha = new ArrayList<>();
    }
}
