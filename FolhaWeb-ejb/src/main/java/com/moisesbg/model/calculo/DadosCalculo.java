package com.moisesbg.model.calculo;

import com.moisesbg.model.calculo.execucao.CalculoExecutado;
import com.moisesbg.model.evento.Evento;
import com.moisesbg.model.funcionario.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DadosCalculo {
    private LocalDate competencia;
    private Funcionario funcionario;
    private LocalDate dataPagamento;
    private List<Evento> listaEventos;
    private BigDecimal baseINSS;
    private BigDecimal baseIRRF;
    private Evento eventoCalcular;
    private CalculoExecutado calculoExecutado;


    public LocalDate getCompetencia() {
        return competencia;
    }

    public void setCompetencia(LocalDate competencia) {
        this.competencia = competencia;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }

    public Evento getEventoCalcular() {
        return eventoCalcular;
    }

    public void setEventoCalcular(Evento eventoCalcular) {
        this.eventoCalcular = eventoCalcular;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public CalculoExecutado getCalculoExecutado() {
        return calculoExecutado;
    }

    public void setCalculoExecutado(CalculoExecutado calculoExecutado) {
        this.calculoExecutado = calculoExecutado;
    }

    public BigDecimal getBaseINSS() {
        return Optional.ofNullable(baseINSS).orElse(BigDecimal.ZERO);
    }

    public BigDecimal getBaseIRRF() {
        return Optional.ofNullable(baseIRRF).orElse(BigDecimal.ZERO);
    }

    public void somarBaseINSS(BigDecimal valor){
        this.baseINSS = Optional.ofNullable(this.baseINSS).orElse(BigDecimal.ZERO)
                .add(Optional.ofNullable(valor).orElse(BigDecimal.ZERO));

    }

    public void diminuirBaseINSS(BigDecimal valor){
        this.baseINSS = Optional.ofNullable(this.baseINSS).orElse(BigDecimal.ZERO)
                .subtract(Optional.ofNullable(valor).orElse(BigDecimal.ZERO));
    }

    public void somarBaseIRRF(BigDecimal valor){
        this.baseIRRF = Optional.ofNullable(this.baseIRRF).orElse(BigDecimal.ZERO)
                .add(Optional.ofNullable(valor).orElse(BigDecimal.ZERO));
    }

    public void diminuirBaseIRRF(BigDecimal valor){
        this.baseIRRF = Optional.ofNullable(this.baseIRRF).orElse(BigDecimal.ZERO)
                .subtract(Optional.ofNullable(valor).orElse(BigDecimal.ZERO));
    }

    public void resetar() {
        this.baseIRRF = null;
        this.baseINSS = null;
        this.calculoExecutado = new CalculoExecutado();
    }

}
