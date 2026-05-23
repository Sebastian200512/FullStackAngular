package co.pei.sebastianpatino.reservation_backend.exception;

/**
 * Thrown when an operation targets a reservation that is already cancelled.
 */
public class ReservationAlreadyCancelledException extends RuntimeException {

    /**
     * Creates an exception for a reservation that is already cancelled.
     *
     * @param id identifier of the already cancelled reservation
     */
    public ReservationAlreadyCancelledException(long id) {
        super("Reservation with id %d is already cancelled".formatted(id));
    }
}
