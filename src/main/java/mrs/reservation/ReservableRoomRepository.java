package mrs.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservableRoomRepository
    extends JpaRepository<ReservableRoom, ReservableRoomId> {

    List<ReservableRoom> findByReservableRoomId_reservedDateOrderByReservableRoomId_roomIdAsc(
        LocalDate reservedDate);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<ReservableRoom> findOneForUpdateByReservableRoomId(ReservableRoomId reservableRoomId);
}
