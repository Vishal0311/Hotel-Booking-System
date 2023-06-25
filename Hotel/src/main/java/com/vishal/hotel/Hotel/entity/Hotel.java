package com.vishal.hotel.Hotel.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "HOTEL_DETAILS")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long hotelId;
    @Column(name = "HOTEL_NAME")
    private String hotelName;
    @Column(name = "HOTEL_ADDRESS")
    private String hotelAddress;
    @Column(name = "HOTEL_TOTAL_ROOMS")
    private int hotelTotalRooms;
    @Column(name = "HOTEL_PHONE_NUMBER")
    private int hotelPhoneNumber;
    @Column(name = "HOTEL_RATING")
    private float hotelRating;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Room> hotelRooms;


    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", hotelAddress='" + hotelAddress + '\'' +
                ", hotelTotalRooms=" + hotelTotalRooms +
                ", hotelPhoneNumber=" + hotelPhoneNumber +
                ", hotelRating=" + hotelRating +
                ", hotelRooms=" + hotelRooms +
                '}';
    }


}
