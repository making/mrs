package mrs.reservation;

import java.time.LocalDate;

public record ReservableRoomId(Integer roomId, LocalDate reservedDate) {

}
