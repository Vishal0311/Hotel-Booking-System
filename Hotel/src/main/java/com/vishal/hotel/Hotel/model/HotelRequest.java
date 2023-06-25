package com.vishal.hotel.Hotel.model;

import com.vishal.hotel.Hotel.entity.Room;
import lombok.Data;


import java.util.List;

@Data
public class HotelRequest {

    private String hotelName;
    private String hotelAddress;
    private int hotelTotalRooms;
    private int hotelPhoneNumber;
    private float hotelRating;
    private List<Room> hotelRooms;

}
