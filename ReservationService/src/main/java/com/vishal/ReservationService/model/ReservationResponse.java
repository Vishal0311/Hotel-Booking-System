package com.vishal.ReservationService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponse {


    private long userId;
    private long hotelId;
    private String checkInTime;
    private String checkOutTime;
    private String checkInDate;
    private String checkOutDate;

}
