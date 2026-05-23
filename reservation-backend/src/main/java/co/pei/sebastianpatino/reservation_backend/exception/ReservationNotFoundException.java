package co.pei.sebastianpatino.reservation_backend.exception;

/**
 * Thrown when a reservation with the given identifier does not exist.
 */
public class ReservationNotFoundException extends RuntimeException {

    /**
     * Creates an exception for a missing reservation.
     *
     * @param id identifier of the reservation that was not found
     */
    public ReservationNotFoundException(long id) {
        super("Reservation not found with id: %d".formatted(id));
    }
}
