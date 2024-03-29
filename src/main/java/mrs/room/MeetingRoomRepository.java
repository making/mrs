package mrs.room;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MeetingRoomRepository {

	private final JdbcClient jdbcClient;

	public MeetingRoomRepository(JdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;
	}

	public Optional<MeetingRoom> findById(Integer roomId) {
		return this.jdbcClient.sql("SELECT room_id, room_name FROM meeting_room WHERE room_id = ?")
			.params(roomId)
			.query((rs, i) -> {
				final MeetingRoom r = new MeetingRoom();
				r.setRoomId(rs.getInt("room_id"));
				r.setRoomName(rs.getString("room_name"));
				return r;
			})
			.optional();
	}

}
