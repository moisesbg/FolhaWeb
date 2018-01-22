package com.moisesbg.model.cargo;

import com.moisesbg.dao.GenericDao;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CargoService {

    @Inject
    private GenericDao<Cargo> dao;

    public void inserir(Cargo cargo) {
        dao.inserir(cargo);
    }

    public Cargo atualizar(Cargo cargo) {
        return dao.atualizar(cargo);
    }

    public void remover(Integer id) {
        dao.remover(id);
    }

    public List<Cargo> listar(){
        return dao.listar();
    }

    public List<Cargo> listarPorNome(String nome){
        QCargo qCargo = QCargo.cargo;
        Predicate predicate = new BooleanBuilder(qCargo.nome.likeIgnoreCase("%"+nome+"%"));

        return dao.listar(predicate).stream()
                .sorted(Comparator.comparing(Cargo::getNome))
                .collect(Collectors.toList());
    }

    public Cargo listarPorId(Integer id) {
        return dao.listarPorId(id);
    }
}
