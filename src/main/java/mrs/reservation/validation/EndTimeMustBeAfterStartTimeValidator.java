package mrs.reservation.validation;

import mrs.reservation.web.ReservationForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EndTimeMustBeAfterStartTimeValidator
    implements ConstraintValidator<EndTimeMustBeAfterStartTime, ReservationForm> {

    private String message;

    @Override
    public void initialize(EndTimeMustBeAfterStartTime constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(ReservationForm value, ConstraintValidatorContext context) {
        if (value.getStartTime() == null || value.getEndTime() == null) {
            return true;
        }
        boolean isEndTimeMustBeAfterStartTime = value.getEndTime()
            .isAfter(value.getStartTime());
        if (!isEndTimeMustBeAfterStartTime) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode("endTime").addConstraintViolation();
        }
        return isEndTimeMustBeAfterStartTime;
    }
}
