package com.vishal.hotel.Hotel.exceptions;


import lombok.Data;

@Data
public class HotelServiceCustomException extends RuntimeException{

    private String errorCode;
    public HotelServiceCustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
