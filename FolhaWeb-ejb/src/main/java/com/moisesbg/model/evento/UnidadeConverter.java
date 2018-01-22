package com.moisesbg.model.evento;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UnidadeConverter implements AttributeConverter<Unidade, String> {

    @Override
    public String convertToDatabaseColumn(Unidade entityValue) {
        switch (entityValue) {
            case AUTOMATICO:
                return "A";
            case DIAS:
                return "D";
            case HORAS:
                return "H";
            default:
                return null;
        }
    }

    @Override
    public Unidade convertToEntityAttribute(String dbValue) {
        switch (dbValue) {
            case "A":
                return Unidade.AUTOMATICO;
            case "D":
                return Unidade.DIAS;
            case "H":
                return Unidade.HORAS;
            default:
                return null;
        }
    }
}
