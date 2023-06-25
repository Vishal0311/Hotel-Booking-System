package com.vishal.ReservationService.external.model;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    @JoinColumn(name = "hotelId",foreignKey = @ForeignKey(name = "roomNumber"))

    private Hotel hotel;
}
