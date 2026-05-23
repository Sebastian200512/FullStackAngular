package co.pei.sebastianpatino.reservation_backend.service;

import co.pei.sebastianpatino.reservation_backend.dto.CreateReservationRequest;
import co.pei.sebastianpatino.reservation_backend.dto.ReservationResponse;
import co.pei.sebastianpatino.reservation_backend.entity.ReservationEntity;
import co.pei.sebastianpatino.reservation_backend.entity.ReservationStatus;
import co.pei.sebastianpatino.reservation_backend.exception.ReservationAlreadyCancelledException;
import co.pei.sebastianpatino.reservation_backend.exception.ReservationConflictException;
import co.pei.sebastianpatino.reservation_backend.exception.ReservationNotFoundException;
import co.pei.sebastianpatino.reservation_backend.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Application service for reservation business operations.
 */
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    /**
     * Creates the service with its required dependencies.
     *
     * @param reservationRepository persistence gateway for reservations
     */
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * Creates a reservation when the requested date and time slot is available.
     *
     * @param request validated reservation data
     * @return the created reservation representation
     * @throws ReservationConflictException if an active reservation already exists for the same date and time
     */
    @Transactional
    public ReservationResponse createReservation(CreateReservationRequest request) {
        if (reservationRepository.existsByDateAndTimeAndStatus(
                request.date(), request.time(), ReservationStatus.ACTIVO)) {
            throw new ReservationConflictException(request.date(), request.time());
        }

        var entity = new ReservationEntity();
        entity.setCustomerName(request.customerName());
        entity.setDate(request.date());
        entity.setTime(request.time());
        entity.setService(request.service());
        entity.setStatus(ReservationStatus.ACTIVO);

        return toResponse(reservationRepository.save(entity));
    }

    /**
     * Cancels an existing reservation by its identifier.
     *
     * @param id reservation identifier
     * @return the cancelled reservation representation
     * @throws ReservationNotFoundException         if no reservation exists with the given id
     * @throws ReservationAlreadyCancelledException if the reservation is already cancelled
     */
    @Transactional
    public ReservationResponse cancelReservation(long id) {
        var entity = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));

        if (entity.getStatus() == ReservationStatus.CANCELADA) {
            throw new ReservationAlreadyCancelledException(id);
        }

        entity.setStatus(ReservationStatus.CANCELADA);
        return toResponse(reservationRepository.save(entity));
    }

    /**
     * Maps a persisted reservation entity to its API representation.
     *
     * @param entity persisted reservation
     * @return reservation response DTO
     */
    private ReservationResponse toResponse(ReservationEntity entity) {
        return new ReservationResponse(
                entity.getId(),
                entity.getCustomerName(),
                entity.getDate(),
                entity.getTime(),
                entity.getService(),
                entity.getStatus()
        );
    }
}
