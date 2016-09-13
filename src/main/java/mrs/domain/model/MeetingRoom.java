package mrs.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MeetingRoom implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roomId;

	private String roomName;

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("MeetingRoom{");
		sb.append("roomId=").append(roomId);
		sb.append(", roomName='").append(roomName).append('\'');
		sb.append('}');
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		MeetingRoom that = (MeetingRoom) o;

		if (roomId != null ? !roomId.equals(that.roomId) : that.roomId != null)
			return false;
		if (roomName != null ? !roomName.equals(that.roomName) : that.roomName != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = roomId != null ? roomId.hashCode() : 0;
		result = 31 * result + (roomName != null ? roomName.hashCode() : 0);
		return result;
	}
}
