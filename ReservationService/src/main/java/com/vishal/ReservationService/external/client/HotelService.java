package com.vishal.ReservationService.external.client;


import com.vishal.ReservationService.external.model.IsAvailable;
import com.vishal.ReservationService.external.model.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {


    @GetMapping("/v1/hotel/room/{hotelId}")
    List<Room> getAllRoomByHotelId(@PathVariable("hotelId") long hotelId);

    @PutMapping("/v1/hotel/room/reduceRoom/{id}")
    void reduceRoom(@PathVariable("id") long id, @RequestParam long roomNumber);

    @PutMapping("/v1/hotel/room/updateAvailability/{hotelId}/{roomNumber}")
    void updateRoomAvailability(@PathVariable("hotelId") long hotelId, @RequestParam long roomNumber);


}
