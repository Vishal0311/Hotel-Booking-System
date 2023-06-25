package com.vishal.hotel.Hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelAndRoomResponse {

    private HotelRequest hotelRequest;
    private List<RoomRequest> roomRequests;

}
