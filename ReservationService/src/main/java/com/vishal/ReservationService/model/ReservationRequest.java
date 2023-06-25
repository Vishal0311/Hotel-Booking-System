package com.vishal.ReservationService.model;


import com.vishal.ReservationService.external.model.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationRequest {

    private long userId;
    private long hotelId;
    private RoomType roomType;
    private String checkInTime;
    private String checkOutTime;
    private String checkInDate;
    private String checkOutDate;

}
