package com.moisesbg.model.calculo.folha;

import com.moisesbg.model.Entidade;
import com.moisesbg.model.funcionario.Funcionario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "folhas_pagamentos")
@SequenceGenerator(name="seq_folhas_pagamentos", sequenceName = "seq_folhas_pagamentos", allocationSize = 1)
public class Folha implements Entidade{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_folhas_pagamentos")
    @Column(name = "id")
    private Integer id;

    @Column(name = "competencia")
    @NotNull(message = "Você deve informar a competência da folha")
    private LocalDate competencia;

    @NotNull(message = "Você deve informar a que funcionário esta folha pertence")
    @ManyToOne(optional = false)
    @JoinColumn(name="i_funcionarios")
    private Funcionario funcionario;

    @Column(name = "data_pagamento")
    @NotNull(message = "Você deve informar a data de pagamento da folha")
    private LocalDate dataPagamento;

    @OneToMany(targetEntity = ItemFolha.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "i_folhas_pagamentos")
    private List<ItemFolha> itensFolha = new ArrayList<>();

    @Override
    public Integer getId() {
        return id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDate getCompetencia() {
        return competencia;
    }

    public void setCompetencia(LocalDate competencia) {
        this.competencia = competencia;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public List<ItemFolha> getItensFolha() {
        return itensFolha;
    }

    public void setItensFolha(List<ItemFolha> itensFolha) {
        this.itensFolha.clear();
        itensFolha.forEach(item -> item.setFolha(this));
        this.itensFolha = itensFolha;
    }
}
