package mrs.reservation;

import mrs.room.MeetingRoom;

import java.io.Serializable;

public class ReservableRoom implements Serializable {

	private MeetingRoom meetingRoom;

	private ReservableRoomId reservableRoomId;

	public ReservableRoom() {

	}

	public ReservableRoom(ReservableRoomId reservableRoomId) {
		this.reservableRoomId = reservableRoomId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ReservableRoom that = (ReservableRoom) o;

		if (reservableRoomId != null ? !reservableRoomId.equals(that.reservableRoomId)
				: that.reservableRoomId != null) {
			return false;
		}

		return true;
	}

	public MeetingRoom getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(MeetingRoom meetingRoom) {
		this.meetingRoom = meetingRoom;
	}

	public ReservableRoomId getReservableRoomId() {
		return reservableRoomId;
	}

	public void setReservableRoomId(ReservableRoomId reservableRoomId) {
		this.reservableRoomId = reservableRoomId;
	}

	@Override
	public int hashCode() {
		return reservableRoomId != null ? reservableRoomId.hashCode() : 0;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ReservableRoom{");
		sb.append("reservableRoomId=").append(reservableRoomId);
		sb.append('}');
		return sb.toString();
	}

}
