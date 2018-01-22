package com.moisesbg.model.formula;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TabelaIrrf {

    private final List<Faixa> faixas = Arrays.asList(
            new Faixa(new BigDecimal("1903.98"), new BigDecimal("0.00"), new BigDecimal("0.00")),
            new Faixa(new BigDecimal("2826.65"), new BigDecimal("0.075"), new BigDecimal("142.80")),
            new Faixa(new BigDecimal("3751.05"), new BigDecimal("0.15"), new BigDecimal("354.80")),
            new Faixa(new BigDecimal("4664.68"), new BigDecimal("0.225"), new BigDecimal("636.13")),
            new Faixa(new BigDecimal("9999999999.99"), new BigDecimal("0.275"), new BigDecimal("869.36")));

    private final BigDecimal descontoDependente = new BigDecimal("189.59");

    private static TabelaIrrf INSTANCE;

    private TabelaIrrf() {

    }

    public static TabelaIrrf get() {
        if (INSTANCE == null) {
            INSTANCE = new TabelaIrrf();
        }
        return INSTANCE;
    }

    Faixa getFaixa(BigDecimal valorAEnquadrar) {
        return faixas.stream()
                .sorted(Comparator.comparing(Faixa::getLimite))
                .filter(faixa -> valorAEnquadrar.compareTo(faixa.getLimite()) == -1)
                .findFirst()
                .orElse(faixas.get(faixas.size() -1));
    }

    BigDecimal getDescontoDependente() {
        return descontoDependente;
    }

    class Faixa {
        BigDecimal limite;
        BigDecimal aliquota;
        BigDecimal desconto;

        Faixa(BigDecimal limite, BigDecimal aliquota, BigDecimal desconto) {
            this.limite = limite;
            this.aliquota = aliquota;
            this.desconto = desconto;
        }

        BigDecimal getLimite() {
            return limite;
        }

        BigDecimal getAliquota() {
            return aliquota;
        }

        BigDecimal getDesconto() {
            return desconto;
        }
    }
}
