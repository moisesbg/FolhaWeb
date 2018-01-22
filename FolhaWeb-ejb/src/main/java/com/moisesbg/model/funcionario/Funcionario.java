package com.moisesbg.model.funcionario;

import com.moisesbg.model.Entidade;
import com.moisesbg.model.cargo.Cargo;
import com.moisesbg.model.departamento.Departamento;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "funcionarios")
@SequenceGenerator(name = "seq_funcionarios", sequenceName = "seq_funcionarios", allocationSize = 1)
public class Funcionario implements Entidade{

    @Id
    @GeneratedValue(generator = "seq_funcionarios",strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "Você deve informar o nome do funcionário")
    @Size(max = 60, message = "O nome do funcionário não pode ser maior do que {max} caracteres")
    @Column(name="nome")
    private String nome;

    @NotNull(message = "Você deve informar a data de admissão do funcionário ${nome}")
    @Column(name="data_admissao")
    private LocalDate dataAdmissao;

    @NotNull(message = "Você deve informar o departamento do funcionário ${nome}")
    @ManyToOne(optional = false)
    @JoinColumn(name="i_departamentos")
    private Departamento departamento;

    @NotNull(message = "Você deve informar o cargo do funcionário ${nome}")
    @ManyToOne(optional = false)
    @JoinColumn(name = "i_cargos")
    private Cargo cargo;

    @NotNull(message = "Você deve informar o salário do funcionário ${nome}")
    @DecimalMin(value = "0.01", message = "O salário do funcionário ${nome} não pode ser inferior a {value}")
    @Column(name="salario")
    private BigDecimal salario;

    @NotNull(message = "Você deve informar a quantidade de horas mensais trabalhadas pelo funcionário ${nome}")
    @Min(value = 1, message = "A quantidade de horas mensais trabalhadas pelo funcionário ${nome} não pode ser inferior a {value}")
    @Max(value = 440, message = "A quantidade de horas mensais trabalhadas pelo funcionário ${nome} não pode ser superior a {value}")
    @Column(name="horas_mensais")
    private Integer horasMensais;

    @NotNull(message = "Você deve informar o sexo do funcionário {$nome}")
    @Column(name="sexo")
    @Convert(converter = SexoConverter.class)
    private Sexo sexo;

    @NotNull(message = "Você deve informar a data de nascimento do funcionário {$nome}")
    @Column(name="data_nascimento")
    private LocalDate dataNascimento;

    @Min(value = 0, message = "A quantidade de dependentes do funcionário ${nome} não pode ser inferior a {value}")
    @Column(name="qtd_dependentes")
    private Integer qtdDependentes;

    @Size(max = 11, message = "O CPF do funcionário ${nome} deve conter exatamente 11 dígitos")
    @Column(name="cpf")
    private String cpf;

    @Column(name="ctps")
    private String ctps;

    @Column(name="uf_ctps")
    @Enumerated(EnumType.STRING)
    private Uf ufCtps;

    @Column(name="data_ctps")
    private LocalDate dataCtps;

    public Funcionario() {

    }

    public Funcionario(Builder builder)  {
        this.id = builder.id;
        this.dataAdmissao = builder.dataAdmissao;
        this.departamento = builder.departamento;
        this.cargo = builder.cargo;
        this.salario = builder.salario;
        this.horasMensais = builder.horasMensais;
        this.qtdDependentes = builder.qtdDependentes;
        this.nome = builder.nome;
        this.sexo = builder.sexo;
        this.dataNascimento = builder.dataNascimento;
        this.cpf = builder.cpf;
        this.ctps = builder.ctps;
        this.ufCtps = builder.ufCtps;
        this.dataCtps = builder.dataCtps;

    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Integer getHorasMensais() {
        return horasMensais;
    }

    public Integer getQtdDependentes() {
        return qtdDependentes;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCtps() {
        return ctps;
    }

    public Uf getUfCtps() {
        return ufCtps;
    }

    public LocalDate getDataCtps() {
        return dataCtps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Funcionario)) return false;

        Funcionario that = (Funcionario) o;

        if (!getNome().equals(that.getNome())) return false;
        if (!getDataAdmissao().equals(that.getDataAdmissao())) return false;
        return getDataNascimento().equals(that.getDataNascimento());
    }

    @Override
    public int hashCode() {
        int result = getNome().hashCode();
        result = 31 * result + getDataAdmissao().hashCode();
        result = 31 * result + getDataNascimento().hashCode();
        return result;
    }

    public static class Builder {
        private Integer id;
        private String nome;
        private LocalDate dataAdmissao;
        private Departamento departamento;
        private Cargo cargo;
        private BigDecimal salario;
        private Integer horasMensais;
        private Integer qtdDependentes;
        private Sexo sexo;
        private LocalDate dataNascimento;
        private String cpf;
        private String ctps;
        private Uf ufCtps;
        private LocalDate dataCtps;

        public Funcionario build() {
            return new Funcionario(this);
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder dataAdmissao(LocalDate dataAdmissao) {
            this.dataAdmissao = dataAdmissao;
            return this;
        }

        public Builder departamento(Departamento departamento) {
            this.departamento = departamento;
            return this;
        }

        public Builder cargo(Cargo cargo) {
            this.cargo = cargo;
            return this;
        }

        public Builder salario(BigDecimal salario) {
            this.salario = salario;
            return this;
        }

        public Builder horasMensais(Integer horasMensais) {
            this.horasMensais = horasMensais;
            return this;
        }

        public Builder qtdDependentes(Integer qtdDependentes) {
            this.qtdDependentes = qtdDependentes;
            return this;
        }

        public Builder sexo(Sexo sexo) {
            this.sexo = sexo;
            return this;
        }

        public Builder dataNascimento(LocalDate dataNascimento) {
            this.dataNascimento = dataNascimento;
            return this;
        }

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder ctps(String ctps) {
            this.ctps = ctps;
            return this;
        }

        public Builder ufCtps(Uf ufCtps) {
            this.ufCtps = ufCtps;
            return this;
        }

        public Builder dataCtps(LocalDate dataCtps) {
            this.dataCtps = dataCtps;
            return this;
        }
    }
}
