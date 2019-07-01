package mrs.reservation.validation;

import java.time.LocalTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ThirtyMinutesUnitValidator
		implements ConstraintValidator<ThirtyMinutesUnit, LocalTime> {
	@Override
	public void initialize(ThirtyMinutesUnit constraintAnnotation) {

	}

	@Override
	public boolean isValid(LocalTime value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return value.getMinute() % 30 == 0;
	}
}
