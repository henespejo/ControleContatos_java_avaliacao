package com.projeto.controleContatos.model.converter;

import com.projeto.controleContatos.model.enums.TipoContato;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoContatoConverter implements AttributeConverter<TipoContato, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoContato tipo) {
        if (tipo == null) {
            return null;
        }
        return tipo.getValor();
    }

    @Override
    public TipoContato convertToEntityAttribute(Integer valor) {
        if (valor == null) {
            return null;
        }
        return TipoContato.getTipo(valor);
    }
}
