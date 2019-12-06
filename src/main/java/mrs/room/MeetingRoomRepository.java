package mrs.room;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MeetingRoomRepository {

    private final JdbcTemplate jdbcTemplate;

    public MeetingRoomRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<MeetingRoom> findById(Integer roomId) {
        try {
            final MeetingRoom meetingRoom = this.jdbcTemplate.queryForObject("SELECT room_id, room_name FROM meeting_room WHERE room_id = ?", (rs, i) -> {
                final MeetingRoom r = new MeetingRoom();
                r.setRoomId(rs.getInt("room_id"));
                r.setRoomName(rs.getString("room_name"));
                return r;
            }, roomId);
            return Optional.ofNullable(meetingRoom);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
