package com.moisesbg.model.evento;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.moisesbg.model.evento.TipoFormula.*;

@Converter(autoApply = true)
public class TipoFormulaConverter  implements AttributeConverter<TipoFormula, Character> {

    @Override
    public Character convertToDatabaseColumn(TipoFormula entityValue) {

        switch (entityValue) {
            case HORAS_NORMAIS:
                return '1';
            case INSS:
                return '2';
            case IRRF:
                return '3';
            case VARIAVEIS:
                return '4';
            default:
                return null;
        }
    }

    @Override
    public TipoFormula convertToEntityAttribute(Character dbValue) {
        switch (dbValue) {
            case '1':
                return HORAS_NORMAIS;
            case '2':
                return INSS;
            case '3':
                return IRRF;
            case '4':
                return VARIAVEIS;
            default:
                return null;
        }
    }
}
