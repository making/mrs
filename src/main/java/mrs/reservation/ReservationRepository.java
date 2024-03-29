package mrs.reservation;

import java.util.List;
import java.util.Optional;

import mrs.room.MeetingRoom;
import mrs.user.RoleName;
import mrs.user.User;
import mrs.user.UserBuilder;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
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
				final ReservableRoomId id = new ReservableRoomId(rs.getInt("room_id"),
						rs.getDate("reserved_date").toLocalDate());
				final User user = UserBuilder.user().userId(rs.getString("user_id")).build();
				final ReservableRoom reservableRoom = new ReservableRoom(id, null);
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
			.params(reservableRoomId.reservedDate(), reservableRoomId.roomId())
			.query((rs, i) -> {
				final int roomId = rs.getInt("room_id");
				final ReservableRoomId id = new ReservableRoomId(roomId, rs.getDate("reserved_date").toLocalDate());
				final MeetingRoom meetingRoom = new MeetingRoom(roomId, rs.getString("room_name"));
				final ReservableRoom reservableRoom = new ReservableRoom(id, meetingRoom);
				final User user = UserBuilder.user()
					.userId(rs.getString("user_id"))
					.password(rs.getString("password"))
					.roleName(RoleName.valueOf(rs.getString("role_name")))
					.firstName(rs.getString("first_name"))
					.lastName(rs.getString("last_name"))
					.build();
				return new Reservation(rs.getInt("reservation_id"), rs.getTime("start_time").toLocalTime(),
						rs.getTime("end_time").toLocalTime(), reservableRoom, user);
			})
			.list();
	}

	public Reservation save(Reservation reservation) {
		final ReservableRoomId id = reservation.reservableRoom().reservableRoomId();
		final User user = reservation.user();
		final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		this.jdbcClient.sql("""
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
			.params(reservation.reservationId(), reservation.startTime(), reservation.endTime(), id.reservedDate(),
					id.roomId(), user.userId())
			.update(keyHolder, "reservation_id");
		return ReservationBuilder.from(reservation).reservationId(keyHolder.getKey().intValue()).build();
	}

	public int delete(Reservation reservation) {
		return this.jdbcClient.sql("DELETE FROM reservation WHERE reservation_id = ?")
			.params(reservation.reservationId())
			.update();
	}

}
