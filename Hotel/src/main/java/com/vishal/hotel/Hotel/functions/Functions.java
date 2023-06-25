package com.vishal.hotel.Hotel.functions;

public class Functions {

    public String removeSpace(String str){
        str = str.trim().replaceAll(" +", " ");
        str = str.replaceAll("\\s","");
        return str;
    }


}
