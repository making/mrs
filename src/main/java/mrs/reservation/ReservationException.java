package mrs.reservation;

public class ReservationException extends RuntimeException {

    public ReservationException(String message) {
        super(message);
    }

    public static class AlreadyReserved extends ReservationException {

        public AlreadyReserved(String message) {
            super(message);
        }
    }

    public static class Unavailable extends ReservationException {

        public Unavailable(String message) {
            super(message);
        }
    }
}
