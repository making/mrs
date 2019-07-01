package mrs.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(
        ReservableRoomId reservableRoomId);
}
