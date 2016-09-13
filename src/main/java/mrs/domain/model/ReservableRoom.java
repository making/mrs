package mrs.domain.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class ReservableRoom implements Serializable {
	@EmbeddedId
	private ReservableRoomId reservableRoomId;

	@ManyToOne
	@MapsId("roomId")
	@JoinColumn(name = "room_id", insertable = false, updatable = false)
	private MeetingRoom meetingRoom;

	public ReservableRoom() {

	}

	public ReservableRoom(ReservableRoomId reservableRoomId) {
		this.reservableRoomId = reservableRoomId;
	}

	public ReservableRoomId getReservableRoomId() {
		return reservableRoomId;
	}

	public void setReservableRoomId(ReservableRoomId reservableRoomId) {
		this.reservableRoomId = reservableRoomId;
	}

	public MeetingRoom getMeetingRoom() {
		return meetingRoom;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ReservableRoom{");
		sb.append("reservableRoomId=").append(reservableRoomId);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ReservableRoom that = (ReservableRoom) o;

		if (reservableRoomId != null ? !reservableRoomId.equals(that.reservableRoomId)
				: that.reservableRoomId != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return reservableRoomId != null ? reservableRoomId.hashCode() : 0;
	}
}
