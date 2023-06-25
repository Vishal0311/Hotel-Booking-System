package com.vishal.hotel.Hotel.model;

import com.vishal.hotel.Hotel.entity.IsAvailable;
import com.vishal.hotel.Hotel.entity.RoomType;
import lombok.Data;

@Data
public class RoomResponse {

    private long roomNumber;
    private IsAvailable isAvailable;
    private RoomType roomType;
    private String roomPrice;

}
