package ec.gbc.house.converters;

import ec.gbc.house.enumeraciones.EstadoRegistro;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class Estado implements AttributeConverter<EstadoRegistro, Integer>{

	@Override
	public Integer convertToDatabaseColumn(EstadoRegistro attribute) {
		return attribute.getValue();
	}

	@Override
	public EstadoRegistro convertToEntityAttribute(Integer dbData) {
		return EstadoRegistro.getEnum(dbData);
	}

}
