package mrs.reservation;

import java.time.LocalDate;
import java.time.LocalTime;

import am.ik.yavi.core.ConstraintViolations;
import am.ik.yavi.core.Validated;
import mrs.user.User;
import mrs.user.UserBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReservationTest {

	@Test
	void validate_valid() {
		int reservationId = 1;
		LocalTime startTime = LocalTime.of(9, 0, 0);
		LocalTime endTime = LocalTime.of(10, 0, 0);
		User user = UserBuilder.user().userId("test").build();
		ReservableRoom reservableRoom = new ReservableRoom(new ReservableRoomId(1, LocalDate.now()), null);
		Validated<Reservation> validated = Reservation.of(reservationId, startTime, endTime, reservableRoom, user);
		assertThat(validated.isValid()).isTrue();
		Reservation reservation = validated.value();
		assertThat(reservation.reservationId()).isEqualTo(reservationId);
		assertThat(reservation.startTime()).isEqualTo(startTime);
		assertThat(reservation.endTime()).isEqualTo(endTime);
		assertThat(reservation.reservableRoom()).isEqualTo(reservableRoom);
		assertThat(reservation.user()).isEqualTo(user);
	}

	@Test
	void validate_invalid_single_fields() {
		LocalTime startTime = LocalTime.of(9, 15, 0);
		LocalTime endTime = LocalTime.of(10, 15, 0);
		Validated<Reservation> validated = Reservation.of(null, startTime, endTime, null, null);
		assertThat(validated.isValid()).isFalse();
		ConstraintViolations violations = validated.errors();
		assertThat(violations).hasSize(3);
		assertThat(violations.get(0).name()).isEqualTo("startTime");
		assertThat(violations.get(0).messageKey()).isEqualTo("datetime.thirtyminutes");
		assertThat(violations.get(1).name()).isEqualTo("endTime");
		assertThat(violations.get(1).messageKey()).isEqualTo("datetime.thirtyminutes");
		assertThat(violations.get(2).name()).isEqualTo("user");
		assertThat(violations.get(2).messageKey()).isEqualTo("object.notNull");
	}

	@Test
	void validate_invalid_cross_fields() {
		LocalTime startTime = LocalTime.of(10, 0, 0);
		LocalTime endTime = LocalTime.of(9, 0, 0);
		User user = UserBuilder.user().userId("test").build();
		Validated<Reservation> validated = Reservation.of(null, startTime, endTime, null, user);
		assertThat(validated.isValid()).isFalse();
		ConstraintViolations violations = validated.errors();
		assertThat(violations).hasSize(1);
		assertThat(violations.get(0).name()).isEqualTo("endTime");
		assertThat(violations.get(0).messageKey()).isEqualTo("reservation.endtimemustbeafterstarttime");
	}

}