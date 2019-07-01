package mrs.reservation;

import mrs.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Reservation {

    private LocalTime endTime;

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "reserved_date"), @JoinColumn(name = "room_id")})
    private ReservableRoom reservableRoom;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    private LocalTime startTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Reservation that = (Reservation) o;

        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) {
            return false;
        }
        if (reservableRoom != null ? !reservableRoom.equals(that.reservableRoom)
            : that.reservableRoom != null) {
            return false;
        }
        if (reservationId != null ? !reservationId.equals(that.reservationId)
            : that.reservationId != null) {
            return false;
        }
        if (startTime != null ? !startTime.equals(that.startTime)
            : that.startTime != null) {
            return false;
        }
        if (user != null ? !user.equals(that.user) : that.user != null) {
            return false;
        }

        return true;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public ReservableRoom getReservableRoom() {
        return reservableRoom;
    }

    public void setReservableRoom(ReservableRoom reservableRoom) {
        this.reservableRoom = reservableRoom;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int result = reservationId != null ? reservationId.hashCode() : 0;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (reservableRoom != null ? reservableRoom.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    public boolean overlap(Reservation target) {
        // 2つの予約の日付・部屋が別であれば重複していない
        if (!Objects.equals(reservableRoom, target.reservableRoom)) {
            return false;
        }
        // 2つの予約の開始時刻と終了時刻が一致する場合は重複とみなす
        if (startTime.equals(target.startTime) && endTime.equals(target.endTime)) {
            return true;
        }
        // 2つの予約の開始時刻と終了時刻が交差する場合、包含関係にある場合は重複とみなす
        return target.endTime.isAfter(startTime) && endTime.isAfter(target.startTime);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Reservation{");
        sb.append("reservationId=").append(reservationId);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", reservableRoom=").append(reservableRoom);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
