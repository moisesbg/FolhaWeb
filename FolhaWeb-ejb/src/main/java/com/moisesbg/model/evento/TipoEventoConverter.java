package com.moisesbg.model.evento;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TipoEventoConverter implements AttributeConverter<TipoEvento, Character> {

    @Override
    public Character convertToDatabaseColumn(TipoEvento entityValue) {
        if(TipoEvento.DESCONTO.equals(entityValue)) {
            return 'D';
        }
        return 'P';
    }

    @Override
    public TipoEvento convertToEntityAttribute(Character dbValue) {
        if('D' == dbValue) {
            return TipoEvento.DESCONTO;
        }
        return TipoEvento.PROVENTO;
    }
}