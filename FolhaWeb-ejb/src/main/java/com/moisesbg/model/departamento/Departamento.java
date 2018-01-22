package com.moisesbg.model.departamento;

import com.moisesbg.model.Entidade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "departamentos")
@SequenceGenerator(name = "seq_departamentos", sequenceName = "seq_departamentos", allocationSize = 1)
public class Departamento implements Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_departamentos")
    private Integer id;

    @Column(name = "nome")
    @NotNull(message="Você deve informar o nome do departamento")
    @Size(max = 60, message = "O nome do departamento não pode ser maior do que {max} caracteres")
    private String nome;



    @Override
    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departamento)) return false;

        Departamento that = (Departamento) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        return getNome().equals(that.getNome());
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getNome().hashCode();
        return result;
    }
}
