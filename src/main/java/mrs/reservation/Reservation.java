package mrs.reservation;

import java.time.LocalTime;
import java.util.Objects;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Validated;
import am.ik.yavi.core.Validator;
import mrs.user.User;

public class Reservation {

	private final Integer reservationId;

	private final LocalTime startTime;

	private final LocalTime endTime;

	private final ReservableRoom reservableRoom;

	private final User user;

	private static Validator<Reservation> validator = ValidatorBuilder.<Reservation>of()
		.constraintOnObject(Reservation::getStartTime, "startTime",
				c -> c.notNull().message("必須です").predicate(ThirtyMinutesUnitConstraints.INSTANCE))
		.constraintOnObject(Reservation::getEndTime, "endTime",
				c -> c.notNull().message("必須です").predicate(ThirtyMinutesUnitConstraints.INSTANCE))
		.constraintOnObject(Reservation::getUser, "user", c -> c.notNull().message("必須です"))
		.constraintOnObject(Reservation::getUser, "user", c -> c.notNull().message("必須です"))
		.constraintOnTarget(EndTimeMustBeAfterStartTimeConstraint.INSTANCE, "endTime")
		.build();

	public static Validated<Reservation> of(Integer reservationId, LocalTime startTime, LocalTime endTime,
			ReservableRoom reservableRoom, User user) {
		return validator.applicative()
			.validate(new Reservation(reservationId, startTime, endTime, reservableRoom, user));
	}

	public Reservation(Integer reservationId, LocalTime startTime, LocalTime endTime, ReservableRoom reservableRoom,
			User user) {
		this.reservationId = reservationId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.reservableRoom = reservableRoom;
		this.user = user;
	}

	public Integer getReservationId() {
		return reservationId;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public ReservableRoom getReservableRoom() {
		return reservableRoom;
	}

	public User getUser() {
		return user;
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

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Reservation{");
		sb.append("reservationId=").append(reservationId);
		sb.append(", startTime=").append(startTime);
		sb.append(", endTime=").append(endTime);
		sb.append(", reservableRoom=").append(reservableRoom);
		sb.append(", user=").append(user);
		sb.append('}');
		return sb.toString();
	}

}
