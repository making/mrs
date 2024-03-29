package mrs.reservation;

import java.util.List;
import java.util.Optional;

import mrs.room.MeetingRoom;
import mrs.user.RoleName;
import mrs.user.User;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepository {

	private final JdbcClient jdbcClient;

	public ReservationRepository(JdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;
	}

	public Optional<Reservation> findById(Integer reservationId) {
		return this.jdbcClient.sql("""
				SELECT
				    r.reservation_id,
				    r.start_time,
				    r.end_time,
				    r.reserved_date,
				    r.room_id,
				    r.user_id
				FROM
				    reservation r
				WHERE
				    r.reservation_id = ?
				""") //
			.param(reservationId)
			.query((rs, i) -> {
				final ReservableRoom reservableRoom = new ReservableRoom();
				final ReservableRoomId id = new ReservableRoomId();
				final User user = new User();
				reservableRoom.setReservableRoomId(id);
				id.setReservedDate(rs.getDate("reserved_date").toLocalDate());
				id.setRoomId(rs.getInt("room_id"));
				user.setUserId(rs.getString("user_id"));
				return new Reservation(reservationId, rs.getTime("start_time").toLocalTime(),
						rs.getTime("end_time").toLocalTime(), reservableRoom, user);
			})
			.optional();
	}

	public List<Reservation> findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(
			ReservableRoomId reservableRoomId) {
		return this.jdbcClient.sql("""
				SELECT
				    r.reservation_id,
				    r.start_time,
				    r.end_time,
				    r.reserved_date,
				    r.room_id,
				    r.user_id,
				    mr.room_name,
				    u.first_name,
				    u.last_name,
				    u.password,
				    u.role_name
				FROM
				    reservation r
				    INNER JOIN
				        reservable_room rr
				    ON  r.reserved_date = rr.reserved_date
				    AND r.room_id = rr.room_id
				    INNER JOIN
				        meeting_room mr
				    ON  r.room_id = mr.room_id
				    INNER JOIN
				        usr u
				    ON  r.user_id = u.user_id
				WHERE
				    rr.reserved_date = ?
				AND rr.room_id = ?
				ORDER BY
				    r.start_time
				""") //
			.params(reservableRoomId.getReservedDate(), reservableRoomId.getRoomId())
			.query((rs, i) -> {
				final ReservableRoom reservableRoom = new ReservableRoom();
				final ReservableRoomId id = new ReservableRoomId();
				final MeetingRoom meetingRoom = new MeetingRoom();
				final User user = new User();
				final int roomId = rs.getInt("room_id");
				reservableRoom.setMeetingRoom(meetingRoom);
				reservableRoom.setReservableRoomId(id);
				id.setReservedDate(rs.getDate("reserved_date").toLocalDate());
				id.setRoomId(roomId);
				meetingRoom.setRoomName(rs.getString("room_name"));
				meetingRoom.setRoomId(roomId);
				user.setUserId(rs.getString("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setPassword(rs.getString("password"));
				user.setRoleName(RoleName.valueOf(rs.getString("role_name")));
				return new Reservation(rs.getInt("reservation_id"), rs.getTime("start_time").toLocalTime(),
						rs.getTime("end_time").toLocalTime(), reservableRoom, user);
			})
			.list();
	}

	public int save(Reservation reservation) {
		final ReservableRoomId id = reservation.getReservableRoom().getReservableRoomId();
		final User user = reservation.getUser();
		return this.jdbcClient.sql("""
				INSERT INTO reservation(
				    reservation_id,
				    start_time,
				    end_time,
				    reserved_date,
				    room_id,
				    user_id
				)
				VALUES(
				    ?,
				    ?,
				    ?,
				    ?,
				    ?,
				    ?
				)
				""")
			.params(reservation.getReservationId(), reservation.getStartTime(), reservation.getEndTime(),
					id.getReservedDate(), id.getRoomId(), user.getUserId())
			.update();
	}

	public int delete(Reservation reservation) {
		return this.jdbcClient.sql("DELETE FROM reservation WHERE reservation_id = ?")
			.params(reservation.getReservationId())
			.update();
	}

}
