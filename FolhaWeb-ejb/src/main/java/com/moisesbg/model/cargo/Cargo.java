package com.moisesbg.model.cargo;

import com.moisesbg.model.Entidade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@SequenceGenerator(name="seq_cargos", sequenceName = "seq_cargos", allocationSize = 1)
@Table(name = "cargos")
public class Cargo implements Entidade {

    @Id
    @GeneratedValue(generator = "seq_cargos", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "nome")
    @NotNull(message="Você deve informar o nome do cargo")
    @Size(min = 1, max = 60, message = "O nome do cargo não pode ser menor do que {min} e maior do que {max} caracteres")
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
        if (!(o instanceof Cargo)) return false;

        Cargo cargo = (Cargo) o;

        if (getId() != null ? !getId().equals(cargo.getId()) : cargo.getId() != null) return false;
        return getNome().equals(cargo.getNome());
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getNome().hashCode();
        return result;
    }
}
