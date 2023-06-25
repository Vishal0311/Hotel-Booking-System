package com.vishal.hotel.Hotel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ROOM")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roomId;
    @Column(name = "ROOM_NUMBER")
    private long roomNumber;
    @Column(name = "ROOM_TYPE")
    private RoomType roomType;
    @Column(name = "ROOM_PRICE")
    private String roomPrice;
    @Column(name = "IS_AVAILABLE")
    private IsAvailable isAvailable;
    @Column(name = "ROOM_ALLOTTED_ID")
    private Integer roomAllottedId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotelId",foreignKey = @ForeignKey(name = "roomId"))

    private Hotel hotel;


    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", roomType=" + roomType +
                ", roomPrice='" + roomPrice + '\'' +
                ", isAvailable=" + isAvailable +
                ", roomAllottedId=" + roomAllottedId +
                ", hotel=" + (hotel != null ? hotel.getHotelName() : null) +
                '}';
    }

}
