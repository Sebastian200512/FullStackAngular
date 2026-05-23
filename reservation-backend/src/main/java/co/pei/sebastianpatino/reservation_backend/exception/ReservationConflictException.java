package co.pei.sebastianpatino.reservation_backend.exception;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Thrown when a reservation cannot be created because the requested date and time slot is already booked.
 */
public class ReservationConflictException extends RuntimeException {

    /**
     * Creates an exception for a conflicting reservation slot.
     *
     * @param date date of the conflicting slot
     * @param time time of the conflicting slot
     */
    public ReservationConflictException(LocalDate date, LocalTime time) {
        super("A reservation already exists for date %s at time %s".formatted(date, time));
    }
}
