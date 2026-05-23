package co.pei.sebastianpatino.reservation_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Request payload for creating a new reservation.
 *
 * @param customerName name of the customer booking the reservation
 * @param date         reservation date
 * @param time         reservation time slot
 * @param service      requested service
 */
public record CreateReservationRequest(
        @NotBlank String customerName,
        @NotNull LocalDate date,
        @NotNull LocalTime time,
        @NotBlank String service
) {
}
