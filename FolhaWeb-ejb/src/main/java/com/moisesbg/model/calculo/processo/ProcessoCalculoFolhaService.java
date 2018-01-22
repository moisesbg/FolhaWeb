package com.moisesbg.model.calculo.processo;

import com.moisesbg.model.calculo.DadosCalculo;
import com.moisesbg.model.calculo.erro.ErroCalculoService;
import com.moisesbg.model.calculo.execucao.CalculoExecutado;
import com.moisesbg.model.calculo.execucao.ExecutorCalculo;
import com.moisesbg.model.calculo.folha.FolhaService;
import com.moisesbg.model.funcionario.Funcionario;
import com.moisesbg.model.funcionario.FuncionarioService;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ProcessoCalculoFolhaService {

    @Inject
    private DadosCalculo dadosCalculo;

    @Inject
    private FuncionarioService funcionarioService;

    @Inject
    private CarregarEventos carregarEventos;

    @Inject
    private ExecutorCalculo executorCalculo;

    @Inject
    private GravarRetornoCalculo gravarRetornoCalculo;

    @Inject
    CalculoExecutado calculoExecutado;

    @Inject
    FolhaService folhaService;

    @Inject
    private ErroCalculoService erroCalculoService;

    public ProcessoCalculoFolhaService(){

    }

    public ProcessoCalculoFolhaService(LocalDate competencia){

    }

    public void executarProcesso(LocalDate competencia, LocalDate dataPagamento) {
        Objects.requireNonNull(competencia, "A competência de cálculo deve ser informada.");
        Objects.requireNonNull(dataPagamento, "A data de pagamento deve ser informada");

        dadosCalculo.setCompetencia(competencia);
        dadosCalculo.setDataPagamento(dataPagamento);

        excluirCalculoAnterior();
        List<Funcionario> funcionarios = funcionarioService.listar();

        dadosCalculo.setListaEventos(carregarEventos.executar());

        funcionarios.forEach(funcionario -> {
            dadosCalculo.setFuncionario(funcionario);
            executorCalculo.executar(dadosCalculo);
            try {
                gravarRetornoCalculo.executar(dadosCalculo);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private void excluirCalculoAnterior() {
        folhaService.remover(dadosCalculo.getCompetencia());
        erroCalculoService.remover(dadosCalculo.getCompetencia());
    }

}
