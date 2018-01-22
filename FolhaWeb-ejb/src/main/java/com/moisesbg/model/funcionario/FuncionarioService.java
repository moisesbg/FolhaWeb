package com.moisesbg.model.funcionario;


import com.moisesbg.dao.GenericDao;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class FuncionarioService {
    @Inject
    public GenericDao<Funcionario> dao;

    public void inserir(Funcionario funcionario) {
        dao.inserir(funcionario);
    }

    public Funcionario atualizar(Funcionario funcionario) {
        return dao.atualizar(funcionario);
    }

    public void remover(Integer id) {
        dao.remover(id);
    }

    public Funcionario listarPorId(Integer id) {
        return dao.listarPorId(id);
    }

    public List<Funcionario> listar() {
        return dao.listar();
    }

    public List<Funcionario> listarPorNome(String nome) {
        QFuncionario q = QFuncionario.funcionario;
        Predicate predicate = new BooleanBuilder(q.nome.likeIgnoreCase("%"+nome+"%"));

        return dao.listar(predicate).stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .collect(Collectors.toList());
    }

}
