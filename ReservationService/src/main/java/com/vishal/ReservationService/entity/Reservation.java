package com.vishal.ReservationService.entity;


import com.vishal.ReservationService.external.model.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "RESERVATION_DETAILS")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reservationId;

    @Column(name = "USER_ID")
    private long userId;
    @Column(name = "HOTEL_ID")
    private long hotelId;
    @Column(name = "ROOM_TYPE")
    private RoomType roomType;
    @Column(name="ROOM_NUMBER")
    private long roomNumber;
    @Column(name = "CHECK_IN_TIME")
    private String checkInTime;
    @Column(name = "CHECK_OUT_TIME")
    private String checkOutTime;
    @Column(name = "CHECK_IN_DATE")
    private String checkInDate;
    @Column(name = "CHECK_OUT_DATE")
    private String checkOutDate;
    @Column(name = "BOOKING_CANCEL")
    private boolean bookingCancel;

}
