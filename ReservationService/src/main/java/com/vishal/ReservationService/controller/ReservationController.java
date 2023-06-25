package com.vishal.ReservationService.controller;


import com.vishal.ReservationService.model.ReservationRequest;
import com.vishal.ReservationService.model.ReservationResponse;
import com.vishal.ReservationService.repository.ReservationRepository;
import com.vishal.ReservationService.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    ReservationRepository reservationRepository;


    @PostMapping
    public ResponseEntity<Long> createReservation(@RequestBody ReservationRequest reservationRequest){
        long reservationId = reservationService.createReservation(reservationRequest);
        return new ResponseEntity<>(reservationId, HttpStatus.CREATED);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable Long reservationId){
        return new ResponseEntity<>(reservationService.getReservationById(reservationId),HttpStatus.FOUND);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId){
        reservationService.deleteReservation(reservationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/{reservationId}")
    public ResponseEntity<Void> updateReservation(@PathVariable Long reservationId,@RequestParam String checkoutDate){
        reservationService.updateReservation(reservationId,checkoutDate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
