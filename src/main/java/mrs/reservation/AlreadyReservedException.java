package mrs.reservation;

public class AlreadyReservedException extends RuntimeException {

    public AlreadyReservedException(String message) {
        super(message);
    }
}
