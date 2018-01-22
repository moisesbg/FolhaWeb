package com.moisesbg.model.calculo.processo;

import com.moisesbg.model.calculo.DadosCalculo;
import com.moisesbg.model.calculo.erro.ErroCalculo;
import com.moisesbg.model.calculo.erro.ErroCalculoService;
import com.moisesbg.model.calculo.folha.Folha;
import com.moisesbg.model.calculo.folha.FolhaService;
import com.moisesbg.model.calculo.folha.ItemFolha;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GravarRetornoCalculo {

    @Inject
    private Folha folha;

    @Inject
    private ErroCalculo erroCalculo;

    @Inject
    private FolhaService folhaService;

    @Inject
    private ErroCalculoService erroCalculoService;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    void executar(DadosCalculo dadosCalculo) throws Throwable {
        if(dadosCalculo.getCalculoExecutado().deuProblemaNoCalculo()) {
            gravarErros(dadosCalculo);
            return;
        }
        gravarFolha(dadosCalculo);

    }

    private void gravarFolha(DadosCalculo dadosCalculo) throws Throwable {
        folha.setFuncionario(dadosCalculo.getFuncionario());
        folha.setDataPagamento(dadosCalculo.getDataPagamento());
        folha.setCompetencia(dadosCalculo.getCompetencia());
        List<ItemFolha> itensFolha = new ArrayList<>();
        dadosCalculo.getCalculoExecutado().getItensFolha()
                .forEach( itemCalculado -> {
                    if(BigDecimal.ZERO.compareTo(itemCalculado.getValoresCalculados().getValorCalculado()) != 0) {
                        itensFolha.add(new ItemFolha.Builder()
                                .evento(itemCalculado.getEvento())
                                .valorReferencia(itemCalculado.getValoresCalculados().getValorReferencia())
                                .valorCalculado(itemCalculado.getValoresCalculados().getValorCalculado())
                                .build());
                    }
                });

        folha.setItensFolha(itensFolha);
        folhaService.inserir(folha);
    }

    private void gravarErros(DadosCalculo dadosCalculo) {
        dadosCalculo.getCalculoExecutado().getErros().forEach(erro ->
                {
                    try {
                        erroCalculoService.inserir(new ErroCalculo.Builder()
                                .funcionario(dadosCalculo.getFuncionario())
                                .competencia(dadosCalculo.getCompetencia())
                                .erro(erro)
                                .build());
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }

                }
        );
    }
}
