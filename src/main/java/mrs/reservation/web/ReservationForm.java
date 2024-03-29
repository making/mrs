package mrs.reservation.web;

import java.io.Serializable;
import java.time.LocalTime;

import am.ik.yavi.core.Validated;
import mrs.reservation.ReservableRoom;
import mrs.reservation.Reservation;
import mrs.user.User;

import org.springframework.format.annotation.DateTimeFormat;

public class ReservationForm implements Serializable {

	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime endTime;

	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime startTime;

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public Validated<Reservation> toReservation(Integer reservationId, ReservableRoom reservableRoom, User user) {
		return Reservation.of(reservationId, this.startTime, this.endTime, reservableRoom, user);
	}

}
