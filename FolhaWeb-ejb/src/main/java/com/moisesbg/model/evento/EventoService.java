package com.moisesbg.model.evento;

import com.moisesbg.dao.GenericDao;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class EventoService {

    @Inject
    private GenericDao<Evento> dao;

    public void inserir(@Valid Evento evento) {
        dao.inserir(evento);
    }

    public Evento atualizar(Evento evento) {
        return dao.atualizar(evento);
    }

    public void remover(Integer id) {
        dao.remover(id);
    }

    public List<Evento> listar() {
        return dao.listar();
    }

    public List<Evento> listarPorNome(String nome) {
        QEvento qEvento = QEvento.evento;
        Predicate predicate = new BooleanBuilder(qEvento.nome.containsIgnoreCase(nome));
        return dao.listar(predicate).stream()
                .sorted(Comparator.comparing(Evento::getNome))
                .collect(Collectors.toList());
    }

    public Evento listarPorId(Integer id) {
        return dao.listarPorId(id);
    }

}
