package com.moisesbg.model.calculo.processo;

import com.moisesbg.model.evento.Evento;
import com.moisesbg.model.evento.EventoService;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class CarregarEventos {

    @Inject
    EventoService eventoService;

    List<Evento> executar() {
        List<Evento> eventos = eventoService.listar();

        List<Evento> teste2 = eventos.stream()
                .sorted(Comparator.comparing(Evento::getMontaBaseInss))
                .collect(Collectors.toList());

        eventos.sort(Comparator.comparing(Evento::getMontaBaseInss)
                .thenComparing(Comparator.comparing(Evento::getMontaBaseIrrf)));

        return eventos;
    }
}
