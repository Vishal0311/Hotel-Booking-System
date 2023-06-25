package com.vishal.hotel.Hotel.model;

import lombok.Data;

@Data
public class HotelResponse {

    private String hotelName;
    private String hotelAddress;
    private int hotelTotalRooms;
    private int hotelPhoneNumber;
    private float hotelRating;
}
