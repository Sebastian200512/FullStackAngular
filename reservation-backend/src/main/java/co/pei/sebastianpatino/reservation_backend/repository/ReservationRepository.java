package co.pei.sebastianpatino.reservation_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.pei.sebastianpatino.reservation_backend.entity.ReservationEntity;
import co.pei.sebastianpatino.reservation_backend.entity.ReservationStatus;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Spring Data repository for {@link ReservationEntity} persistence.
 */
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    /**
     * Checks whether a reservation exists for the given date, time, and status.
     *
     * @param date   reservation date
     * @param time   reservation time
     * @param status reservation status
     * @return {@code true} if a matching reservation exists
     */
    boolean existsByDateAndTimeAndStatus(LocalDate date, LocalTime time, ReservationStatus status);
}
