package com.vishal.ReservationService.service;

import com.vishal.ReservationService.model.ReservationRequest;
import com.vishal.ReservationService.model.ReservationResponse;

public interface ReservationService {
    long createReservation(ReservationRequest reservationRequest);

    ReservationResponse getReservationById(Long reservationId);

    void deleteReservation(Long reservationId);

    void updateReservation(Long reservationId, String checkoutDate);
}
