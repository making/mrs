package mrs.domain.service.room;

import java.time.LocalDate;
import java.util.List;

import mrs.domain.model.MeetingRoom;
import mrs.domain.model.ReservableRoom;
import mrs.domain.repository.room.MeetingRoomRepository;
import mrs.domain.repository.room.ReservableRoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoomService {
	@Autowired
	MeetingRoomRepository meetingRoomRepository;

	@Autowired
	ReservableRoomRepository reservableRoomRepository;

	public MeetingRoom findMeetingRoom(Integer roomId) {
		return meetingRoomRepository.findOne(roomId);
	}

	public List<ReservableRoom> findReservableRooms(LocalDate date) {
		return reservableRoomRepository
				.findByReservableRoomId_reservedDateOrderByReservableRoomId_roomIdAsc(
						date);
	}
}
