package com.moisesbg.model.departamento;

import com.moisesbg.dao.GenericDao;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class DepartamentoService {

    @Inject
    private GenericDao<Departamento> dao;

    public void inserir(Departamento departamento) {
        dao.inserir(departamento);
    }

    public Departamento atualizar(Departamento departamento) {
        return dao.atualizar(departamento);
    }

    public void remover(Integer id){
        dao.remover(id);
    }

    public List<Departamento> listar() {
        return dao.listar();
    }

    public List<Departamento> listarPorNome(String nome) {
        QDepartamento q = QDepartamento.departamento;
        Predicate predicate = new BooleanBuilder(q.nome.likeIgnoreCase("%"+nome+"%"));

        return dao.listar(predicate).stream()
                .sorted(Comparator.comparing(Departamento::getNome))
                .collect(Collectors.toList());
    }

    public Departamento listarPorId(Integer id) {
        return dao.listarPorId(id);
    }

}
