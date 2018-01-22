package com.moisesbg.model.calculo.execucao;


import com.moisesbg.model.evento.Evento;
import com.moisesbg.model.formula.RetornoCalculoEvento;

public class ItemFolhaCalculada {

    private Evento evento;

    private RetornoCalculoEvento valoresCalculados;

    ItemFolhaCalculada(Evento evento, RetornoCalculoEvento valoresCalculados) {
        this.evento = evento;
        this.valoresCalculados = valoresCalculados;
    }

    public Evento getEvento() {
        return evento;
    }

    public RetornoCalculoEvento getValoresCalculados() {
        return valoresCalculados;
    }
}
