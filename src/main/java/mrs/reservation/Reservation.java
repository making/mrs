package mrs.reservation;

import java.time.LocalTime;
import java.util.Objects;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Validated;
import am.ik.yavi.core.Validator;
import jakarta.annotation.Nullable;
import mrs.user.User;
import org.jilt.Builder;
import org.jilt.BuilderStyle;
import org.jilt.Opt;

@Builder(style = BuilderStyle.STAGED_PRESERVING_ORDER, toBuilder = "from")
public record Reservation(@Nullable @Opt Integer reservationId, LocalTime startTime, LocalTime endTime,
		ReservableRoom reservableRoom, User user) {

	private static Validator<Reservation> validator = ValidatorBuilder.<Reservation>of()
		.constraintOnObject(Reservation::startTime, "startTime",
				c -> c.notNull().message("必須です").predicate(ThirtyMinutesUnitConstraints.INSTANCE))
		.constraintOnObject(Reservation::endTime, "endTime",
				c -> c.notNull().message("必須です").predicate(ThirtyMinutesUnitConstraints.INSTANCE))
		.constraintOnObject(Reservation::user, "user", c -> c.notNull().message("必須です"))
		.constraintOnTarget(EndTimeMustBeAfterStartTimeConstraint.INSTANCE, "endTime")
		.build();

	@SuppressWarnings("NullAway")
	public static Validated<Reservation> of(@Nullable Integer reservationId, @Nullable LocalTime startTime,
			@Nullable LocalTime endTime, ReservableRoom reservableRoom, @Nullable User user) {
		return validator.applicative()
			.validate(new Reservation(reservationId, startTime, endTime, reservableRoom, user));
	}

	public boolean overlap(Reservation target) {
		// 2つの予約の日付・部屋が別であれば重複していない
		if (!Objects.equals(reservableRoom, target.reservableRoom)) {
			return false;
		}
		// 2つの予約の開始時刻と終了時刻が一致する場合は重複とみなす
		if (startTime.equals(target.startTime) && endTime.equals(target.endTime)) {
			return true;
		}
		// 2つの予約の開始時刻と終了時刻が交差する場合、包含関係にある場合は重複とみなす
		return target.endTime.isAfter(startTime) && endTime.isAfter(target.startTime);
	}

}
