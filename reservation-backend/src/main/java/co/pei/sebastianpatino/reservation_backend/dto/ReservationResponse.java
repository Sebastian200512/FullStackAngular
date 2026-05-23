package co.pei.sebastianpatino.reservation_backend.dto;

import co.pei.sebastianpatino.reservation_backend.entity.ReservationStatus;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * API representation of a reservation.
 *
 * @param id           unique reservation identifier
 * @param customerName name of the customer
 * @param date         reservation date
 * @param time         reservation time slot
 * @param service      booked service
 * @param status       current reservation status
 */
public record ReservationResponse(
        long id,
        String customerName,
        LocalDate date,
        LocalTime time,
        String service,
        ReservationStatus status
) {
}
