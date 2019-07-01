package mrs.reservation;

public class UnavailableReservationException extends RuntimeException {

    public UnavailableReservationException(String message) {
        super(message);
    }
}
