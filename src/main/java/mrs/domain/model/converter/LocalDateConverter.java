package mrs.domain.model.converter;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate date) {
		return date == null ? null : Date.valueOf(date);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date value) {
		return value == null ? null : value.toLocalDate();
	}
}
