package com.vishal.hotel.Hotel.model;


import com.vishal.hotel.Hotel.entity.IsAvailable;
import com.vishal.hotel.Hotel.entity.RoomType;
import lombok.Data;

@Data
public class RoomRequest {

    private long roomNumber;
    private RoomType roomType;
    private String roomPrice;
    private Integer hotelRefId;
    private IsAvailable isAvailable;
    private Integer roomAllottedId;
}
