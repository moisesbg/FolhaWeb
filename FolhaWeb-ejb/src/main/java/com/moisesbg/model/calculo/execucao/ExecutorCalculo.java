package com.moisesbg.model.calculo.execucao;

import com.moisesbg.model.calculo.DadosCalculo;

import javax.inject.Inject;

public class ExecutorCalculo {
    @Inject
    private CalcularEvento calcularEvento;

    public void executar(DadosCalculo dadosCalculo) {
        dadosCalculo.resetar();
        dadosCalculo.getListaEventos().forEach(evento -> {
            dadosCalculo.setEventoCalcular(evento);
            try {
                dadosCalculo.getCalculoExecutado().getItensFolha().add(
                        new ItemFolhaCalculada(evento, calcularEvento.executar(dadosCalculo)));
            } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                dadosCalculo.getCalculoExecutado().getErros().add(e.getMessage());
                e.printStackTrace();
            }

        });
    }
}
