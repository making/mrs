package mrs.reservation;

import am.ik.yavi.core.CustomConstraint;

public enum EndTimeMustBeAfterStartTimeConstraint implements CustomConstraint<Reservation> {

	INSTANCE;

	@Override
	public String defaultMessageFormat() {
		return "終了時刻は開始時刻より後にしてください";
	}

	@Override
	public String messageKey() {
		return "reservation.endtimemustbeafterstarttime";
	}

	@Override
	public boolean test(Reservation value) {
		if (value.startTime() == null || value.endTime() == null) {
			return true;
		}
		return value.endTime().isAfter(value.startTime());
	}

}
