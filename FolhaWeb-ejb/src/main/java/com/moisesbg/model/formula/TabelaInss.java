package com.moisesbg.model.formula;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TabelaInss {

     private final List<Faixa> faixas = Arrays.asList(
             new Faixa(new BigDecimal("1556.94"), new BigDecimal("0.08")),
             new Faixa(new BigDecimal("2594.92"), new BigDecimal("0.09")),
             new Faixa(new BigDecimal("5189.82"), new BigDecimal("0.11")));

    private final BigDecimal tetoInss = faixas.get(faixas.size() -1).getLimite();

    private static TabelaInss INSTANCE;

    private TabelaInss() {

    }

    public static TabelaInss get() {
        if (INSTANCE == null) {
            INSTANCE = new TabelaInss();
        }
        return INSTANCE;
    }

    Faixa getFaixa(BigDecimal valorAEnquadrar) {
        return faixas.stream()
                .sorted(Comparator.comparing(Faixa::getLimite))
                .filter(item -> valorAEnquadrar.compareTo(item.getLimite()) == -1)
                .findFirst()
                .orElse(faixas.get(faixas.size() -1));
    }

    BigDecimal getTetoInss() {
        return tetoInss;
    }

    class Faixa {
        private BigDecimal limite;
        private BigDecimal aliquota;

        Faixa(BigDecimal limite, BigDecimal aliquota) {
            this.limite = limite;
            this.aliquota = aliquota;
        }

        BigDecimal getLimite() {
            return limite;
        }

        BigDecimal getAliquota() {
            return aliquota;
        }
    }

}
