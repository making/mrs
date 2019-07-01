package mrs.reservation;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class ReservableRoomId implements Serializable {

    private LocalDate reservedDate;

    private Integer roomId;

    public ReservableRoomId(Integer roomId, LocalDate reservedDate) {
        this.roomId = roomId;
        this.reservedDate = reservedDate;
    }

    public ReservableRoomId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReservableRoomId that = (ReservableRoomId) o;

        if (reservedDate != null ? !reservedDate.equals(that.reservedDate)
            : that.reservedDate != null) {
            return false;
        }
        if (roomId != null ? !roomId.equals(that.roomId) : that.roomId != null) {
            return false;
        }

        return true;
    }

    public LocalDate getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(LocalDate reservedDate) {
        this.reservedDate = reservedDate;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    @Override
    public int hashCode() {
        int result = roomId != null ? roomId.hashCode() : 0;
        result = 31 * result + (reservedDate != null ? reservedDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReservableRoomId{");
        sb.append("roomId=").append(roomId);
        sb.append(", reservedDate=").append(reservedDate);
        sb.append('}');
        return sb.toString();
    }
}
