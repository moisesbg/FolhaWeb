package com.moisesbg.model.calculo.erro;

import javax.ejb.Stateless;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ErroCalculoService {

    //@Inject
    //private GenericDao<ErroCalculo> dao;

    public List<ErroCalculo> listarPorCompetencia(LocalDate competencia){
        return new ArrayList<ErroCalculo>();
        /*
        QErroCalculo qErroCalculo = QErroCalculo.erroCalculo;
        Predicate predicate = new BooleanBuilder(qErroCalculo.competencia.eq(competencia));

        return dao.listar(predicate).stream()
                .sorted(Comparator.comparing(erro -> erro.getFuncionario().getNome()))
                .collect(Collectors.toList());*/
    }

    public void inserir(ErroCalculo erro) {
        //dao.inserir(erro);
    }

    public void remover(Integer id) {
        //dao.remover(id);
    }

    public void remover(LocalDate competencia) {
        listarPorCompetencia(competencia).forEach( erro ->
                remover(erro.getId())
        );
    }


}
