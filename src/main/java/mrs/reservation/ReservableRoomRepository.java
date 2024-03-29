package mrs.reservation;

import mrs.room.MeetingRoom;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservableRoomRepository {

	private final JdbcClient jdbcClient;

	private final RowMapper<ReservableRoom> rowMapper = (rs, i) -> {
		final int roomId = rs.getInt("room_id");
		final MeetingRoom r = new MeetingRoom();
		final ReservableRoom rr = new ReservableRoom();
		final ReservableRoomId id = new ReservableRoomId();
		r.setRoomId(roomId);
		r.setRoomName(rs.getString("room_name"));
		rr.setReservableRoomId(id);
		rr.setMeetingRoom(r);
		id.setRoomId(roomId);
		id.setReservedDate(rs.getDate("reserved_date").toLocalDate());
		return rr;
	};

	public ReservableRoomRepository(JdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;
	}

	public Optional<ReservableRoom> findOneForUpdateByReservableRoomId(ReservableRoomId reservableRoomId) {
		return this.jdbcClient.sql("""
				SELECT
				    mr.room_id,
				    mr.room_name,
				    rr.reserved_date
				FROM
				    meeting_room AS mr,
				    reservable_room AS rr
				WHERE
				    rr.reserved_date = ?
				AND rr.room_id = ?
				AND rr.room_id = mr.room_id
				FOR UPDATE
				""")
			.params(reservableRoomId.getReservedDate(), reservableRoomId.getRoomId())
			.query(rowMapper)
			.optional();
	}

	public List<ReservableRoom> findByReservableRoomId_reservedDateOrderByReservableRoomId_roomIdAsc(
			LocalDate reservedDate) {
		return this.jdbcClient.sql("""
				SELECT mr.room_id, mr.room_name, rr.reserved_date FROM meeting_room AS mr, reservable_room AS rr
				WHERE rr.reserved_date = ? AND mr.room_id = rr.room_id
				ORDER BY room_id
				""") //
			.params(reservedDate)
			.query(rowMapper)
			.list();
	}

}
