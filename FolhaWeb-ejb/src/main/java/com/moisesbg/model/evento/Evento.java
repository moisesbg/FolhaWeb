package com.moisesbg.model.evento;

import com.moisesbg.model.Entidade;
import com.moisesbg.model.SimNao;
import com.moisesbg.model.SimNaoConverter;
import com.moisesbg.model.formula.IFormulaCalculoEvento;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "eventos")
@SequenceGenerator(name = "seq_eventos", sequenceName = "seq_eventos", allocationSize = 1)
public class Evento implements Entidade{

    @Id
    @GeneratedValue(generator = "seq_eventos", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "Você deve informar o nome do evento")
    @Column(name = "nome")
    @Size(max = 60, message = "O nome do evento não pode ser maior do que {max} caracteres")
    private String nome;

    @NotNull(message = "Você deve informar o tipo do evento")
    @Column(name = "tipo_evento")
    @Convert(converter = TipoEventoConverter.class)
    private TipoEvento tipoEvento;

    @NotNull(message = "Você deve informar a unidade do evento")
    @Column(name = "unidade")
    @Convert(converter = UnidadeConverter.class)
    private Unidade unidade;

    @NotNull(message = "Você deve informar se o evento monta base para IRRF")
    @Column(name = "monta_base_irrf")
    @Convert(converter = SimNaoConverter.class)
    private SimNao montaBaseIrrf = SimNao.SIM;

    @NotNull(message = "Você deve informar se o evento monta base para INSS")
    @Column(name = "monta_base_inss")
    @Convert(converter = SimNaoConverter.class)
    private SimNao montaBaseInss;

    @NotNull(message = "Você deve informar o tipo da fórmula")
    @Column(name = "tipo_formula")
    @Convert(converter = TipoFormulaConverter.class)
    private TipoFormula tipoFormula;

    @Transient
    private IFormulaCalculoEvento formula;

    public Evento() {}

    private Evento(Builder builder) {
        id = builder.id;
        nome = builder.nome;
        tipoEvento = builder.tipoEvento;
        unidade = builder.unidade;
        montaBaseIrrf = builder.montaBaseIrrf;
        montaBaseInss = builder.montaBaseInss;
        tipoFormula = builder.tipoFormula;

    }

    @Override
    public Integer getId() {
        return id;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public String getNome() {
        return nome;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public SimNao getMontaBaseIrrf() {
        return montaBaseIrrf;
    }

    public SimNao getMontaBaseInss() {
        return montaBaseInss;
    }

    public TipoFormula getTipoFormula() {
        return tipoFormula;
    }

    public IFormulaCalculoEvento getFormula() {
        return formula;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public void setMontaBaseIrrf(SimNao montaBaseIrrf) {
        this.montaBaseIrrf = montaBaseIrrf;
    }

    public void setMontaBaseInss(SimNao montaBaseInss) {
        this.montaBaseInss = montaBaseInss;
    }

    public void setTipoFormula(TipoFormula tipoFormula) {
        this.tipoFormula = tipoFormula;
    }

    public void setFormula(IFormulaCalculoEvento formula) {
        this.formula = formula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evento)) return false;

        Evento evento = (Evento) o;

        if (getId() != null ? !getId().equals(evento.getId()) : evento.getId() != null) return false;
        if (!nome.equals(evento.nome)) return false;
        return getTipoEvento() == evento.getTipoEvento();
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + nome.hashCode();
        result = 31 * result + getTipoEvento().hashCode();
        return result;
    }

    public static class Builder {
        private Integer id;
        private String nome;
        private TipoEvento tipoEvento;
        private Unidade unidade;
        private SimNao montaBaseIrrf;
        private SimNao montaBaseInss;
        private TipoFormula tipoFormula;

        public Evento build() {
            return new Evento(this);
        }

        public Builder id (Integer id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder tipoEvento(TipoEvento tipoEvento) {

            this.tipoEvento = tipoEvento;
            return this;
        }

        public Builder unidade(Unidade unidade) {
            this.unidade = unidade;
            return this;
        }

        public Builder montaBaseIrrf(SimNao montaBaseIRRF) {
            this.montaBaseIrrf = montaBaseIRRF;
            return this;
        }

        public Builder montaBaseInss(SimNao montaBaseINSS) {
            this.montaBaseInss = montaBaseINSS;
            return this;
        }

        public Builder tipoFormula(TipoFormula tipoFormula) {
            this.tipoFormula = tipoFormula;
            return this;
        }

    }
}
