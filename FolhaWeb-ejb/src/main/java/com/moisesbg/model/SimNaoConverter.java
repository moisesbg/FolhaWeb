package com.moisesbg.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class SimNaoConverter  implements AttributeConverter<SimNao, Character> {
    @Override
    public Character convertToDatabaseColumn(SimNao entityValue) {
        if(SimNao.NAO.equals(entityValue)) {
            return 'N';
        }
        return 'S';
    }

    @Override
    public SimNao convertToEntityAttribute(Character dbValue) {
        if('N' == dbValue) {
            return SimNao.NAO;
        }
        return SimNao.SIM;
    }
}
