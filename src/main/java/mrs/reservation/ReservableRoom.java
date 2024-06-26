package mrs.reservation;

import java.util.Objects;

import jakarta.annotation.Nullable;
import mrs.room.MeetingRoom;

public record ReservableRoom(ReservableRoomId reservableRoomId, @Nullable MeetingRoom meetingRoom) {
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ReservableRoom that))
			return false;
		return Objects.equals(reservableRoomId, that.reservableRoomId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(reservableRoomId);
	}
}
