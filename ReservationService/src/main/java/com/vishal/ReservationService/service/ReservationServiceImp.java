package com.vishal.ReservationService.service;


import com.vishal.ReservationService.entity.Reservation;
import com.vishal.ReservationService.external.client.HotelService;
import com.vishal.ReservationService.external.model.IsAvailable;
import com.vishal.ReservationService.external.model.Room;
import com.vishal.ReservationService.model.ReservationRequest;
import com.vishal.ReservationService.model.ReservationResponse;
import com.vishal.ReservationService.repository.ReservationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.vishal.ReservationService.external.model.IsAvailable.False;
import static com.vishal.ReservationService.external.model.IsAvailable.True;
import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Log4j2
public class ReservationServiceImp implements ReservationService{

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private HotelService hotelService;




    @Override
    public long createReservation(ReservationRequest reservationRequest) {

        //Reservation Entity -> Save the data with isAvailable False
        //Hotel Service - Block room (Set isAvailable to False)
        //Payment Service -> Payments -> Success-> COMPLETE, Else
        //CANCELLED


        log.info("Creating Reservation");


        List<Room> room =  hotelService.getAllRoomByHotelId(reservationRequest.getHotelId());
        log.info("LOG FOR HOTEL DETAIL: "+room);

        long roomNumber ;

        long reservationId=0;

        for(int i=0;i< room.size();i++){
            if(room.get(i).getRoomType().equals(reservationRequest.getRoomType())
            && room.get(i).getIsAvailable().equals(True)){

                roomNumber = room.get(i).getRoomNumber();
                log.info("roomNumber: "+roomNumber);
                Reservation reservation = Reservation.builder()
                        .userId(reservationRequest.getUserId())
                        .roomType(reservationRequest.getRoomType())
                        .roomNumber(roomNumber)
                        .checkInDate(reservationRequest.getCheckInDate())
                        .checkOutDate(reservationRequest.getCheckOutDate())
                        .checkInTime(reservationRequest.getCheckInTime())
                        .checkOutTime(reservationRequest.getCheckOutTime())
                        .hotelId(reservationRequest.getHotelId())
                        .build();

                hotelService.reduceRoom(reservationRequest.getHotelId(),roomNumber);

                reservationRepository.save(reservation);
                log.info("Reservation added");
                reservationId = reservation.getReservationId();

                break;

            }



        }



        return reservationId;
    }

    @Override
    public ReservationResponse getReservationById(Long reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(()->new RuntimeException("User not found with id: "+reservationId));

        ReservationResponse reservationResponse = new ReservationResponse();

        copyProperties(reservation,reservationResponse);

        return reservationResponse;

    }

    @Override
    public void deleteReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(()->new RuntimeException("Reservation Not found"));
        log.info("reservation: "+reservation);
        reservationRepository.delete(reservation);
        hotelService.updateRoomAvailability(reservation.getHotelId(),reservation.getRoomNumber());
    }

    @Override
    public void updateReservation(Long reservationId, String checkoutDate) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(()-> new RuntimeException("Reservation not found"));
        log.info("reservation: "+reservation);
        reservation.setCheckOutDate(checkoutDate);
        reservationRepository.save(reservation);
        log.info("Reservation updated");

    }
}
