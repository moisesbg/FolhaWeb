package com.moisesbg.model.calculo.folha;

import com.moisesbg.dao.GenericDao;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class FolhaService {
    @Inject
    private GenericDao<Folha> dao;

    public Folha listarFolhaFuncionario(LocalDate competencia, Integer funcionarioId){
        QFolha qFolha = QFolha.folha;
        Predicate predicate = new BooleanBuilder(qFolha.competencia.eq(competencia))
                .and(qFolha.funcionario.id.eq(funcionarioId));

        return dao.listar(predicate).stream().findFirst().orElse(null);
    }

    public void inserir(Folha folha) {
        dao.inserir(folha);
    }

    public void remover(Integer id) {
        dao.remover(id);
    }

    public void remover(LocalDate competencia) {
        QFolha qFolha = QFolha.folha;
        Predicate predicate = new BooleanBuilder(qFolha.competencia.eq(competencia));

        List<Folha> folhas = dao.listar(predicate);
        int totalFolhas = dao.listar(predicate).size();

        for (int indice = totalFolhas - 1; indice >= 0; indice--) {
            remover(folhas.get(indice).getId());
        }
    }

}
