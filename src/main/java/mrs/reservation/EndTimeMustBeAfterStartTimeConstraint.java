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
		if (value.getStartTime() == null || value.getEndTime() == null) {
			return true;
		}
		return value.getEndTime().isAfter(value.getStartTime());
	}
}
