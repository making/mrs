package mrs.reservation;

import java.time.LocalTime;

import am.ik.yavi.core.CustomConstraint;

public enum ThirtyMinutesUnitConstraints implements CustomConstraint<LocalTime> {

	INSTANCE;

	@Override
	public String defaultMessageFormat() {
		return "30分単位で入力してください";
	}

	@Override
	public String messageKey() {
		return "datetime.thirtyminutes";
	}

	@Override
	public boolean test(LocalTime value) {
		if (value == null) {
			return true;
		}
		return value.getMinute() % 30 == 0;
	}

}
