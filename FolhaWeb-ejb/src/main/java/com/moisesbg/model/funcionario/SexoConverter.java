package com.moisesbg.model.funcionario;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class SexoConverter  implements AttributeConverter<Sexo, Character> {
    @Override
    public Character convertToDatabaseColumn(Sexo entityValue) {
        switch (entityValue) {
            case MASCULINO:
                return 'M';
            case FEMININO:
                return 'F';
            default:
                return null;
        }
    }

    @Override
    public Sexo convertToEntityAttribute(Character dbValue) {
        switch (dbValue) {
            case 'M':
                return Sexo.MASCULINO;
            case 'F':
                return Sexo.FEMININO;
            default:
                return null;
        }
    }
}
