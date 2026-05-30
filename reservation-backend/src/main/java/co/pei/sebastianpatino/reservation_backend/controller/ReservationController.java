package co.pei.sebastianpatino.reservation_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.pei.sebastianpatino.reservation_backend.dto.CreateReservationRequest;
import co.pei.sebastianpatino.reservation_backend.dto.ReservationResponse;
import co.pei.sebastianpatino.reservation_backend.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
   public ReservationResponse createReservation(@RequestBody CreateReservationRequest request) {
    return reservationService.createReservation(request);
   }

   @DeleteMapping("/{id}")
   public ReservationResponse cancelReservation(@PathVariable("id") long id) {
    return reservationService.cancelReservation(id);
   }

   @GetMapping
   public List<ReservationResponse> getAllReservations() {
    return reservationService.getAllReservations();
   }
}
