package com.moisesbg.model.calculo.erro;

import com.moisesbg.model.funcionario.Funcionario;

import java.time.LocalDate;

public class ErroCalculo {

    private Integer id;

    private LocalDate competencia;

    private Funcionario funcionario;

    private String erro;

    public ErroCalculo() {

    }

    public ErroCalculo(Builder builder) {
        this.id = builder.id;
        this.funcionario = builder.funcionario;
        this.competencia = builder.competencia;
        this.erro = builder.erro;

    }

    public Integer getId() {
        return id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public LocalDate getCompetencia() {
        return competencia;
    }

    public String getErro() {
        return erro;
    }

    public static class Builder {
        private Integer id;
        private Funcionario funcionario;
        private LocalDate competencia;
        private String erro;

        public ErroCalculo build() {
            return new ErroCalculo(this);
        }

        public ErroCalculo.Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public ErroCalculo.Builder funcionario(Funcionario funcionario) {
            this.funcionario = funcionario;
            return this;
        }

        public ErroCalculo.Builder competencia(LocalDate competencia) {
            this.competencia = competencia;
            return this;
        }

        public ErroCalculo.Builder erro(String erro) {
            this.erro  = erro;
            return this;
        }
    }
}

/*
@Entity
@Table(name = "erros_calculo")
@SequenceGenerator(name = "seq_erros_calculo", sequenceName = "seq_erros_calculo", allocationSize = 1)
public class ErroCalculo implements Entidade {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "seq_erros_calculo", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "competencia")
    @NotNull(message = "Você deve informar a competência da folha em que ocorreu o erro no processamento")
    private LocalDate competencia;

    @NotNull(message = "Você deve informar para qual funcionário ocorreu o erro no processamento da folha")
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @Column(name = "i_funcionarios")
    private Funcionario funcionario;

    @Column(name="erro")
    @NotNull(message = "Você deve informar o erro que ocorrreu no processamento da folha de pagamento")
    @Size(max = 1000, message = "A mensagem não pode ser maior do que {max} caracteres")
    private String erro;

    public ErroCalculo() {

    }

    public ErroCalculo(Builder builder) {
        this.id = builder.id;
        this.funcionario = builder.funcionario;
        this.competencia = builder.competencia;
        this.erro = builder.erro;

    }

    @Override
    public Integer getId() {
        return id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public LocalDate getCompetencia() {
        return competencia;
    }

    public String getErro() {
        return erro;
    }

    public static class Builder {
        private Integer id;
        private Funcionario funcionario;
        private LocalDate competencia;
        private String erro;

        public ErroCalculo build() {
            return new ErroCalculo(this);
        }

        public ErroCalculo.Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public ErroCalculo.Builder funcionario(Funcionario funcionario) {
            this.funcionario = funcionario;
            return this;
        }

        public ErroCalculo.Builder competencia(LocalDate competencia) {
            this.competencia = competencia;
            return this;
        }

        public ErroCalculo.Builder erro(String erro) {
            this.erro  = erro;
            return this;
        }
    }
}
*/