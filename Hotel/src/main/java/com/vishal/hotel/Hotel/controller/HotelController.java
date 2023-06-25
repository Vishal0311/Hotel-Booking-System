package com.vishal.hotel.Hotel.controller;

import com.vishal.hotel.Hotel.entity.IsAvailable;
import com.vishal.hotel.Hotel.model.HotelAndRoomRequest;
import com.vishal.hotel.Hotel.model.HotelResponse;
import com.vishal.hotel.Hotel.model.RoomResponse;
import com.vishal.hotel.Hotel.repository.HotelRepository;
import com.vishal.hotel.Hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelRepository hotelRepository;



    @PostMapping
    public ResponseEntity<Long> addHotelAndRooms(@RequestBody HotelAndRoomRequest hotelAndRoomRequest){
        long hotelId = hotelService.addHotelAndRooms(hotelAndRoomRequest.getHotelRequest(),hotelAndRoomRequest.getRoomRequests());
        return new ResponseEntity<>(hotelId,HttpStatus.CREATED);
    }

    @GetMapping("/allHotel")
    public List<HotelResponse> getAllHotels(){
        return hotelService.getAllHotels();
    }

    @GetMapping("/{hotelName}")
    public List<HotelResponse> getHotelByNameOrAddress(@PathVariable("hotelName") String hotelName){
        return hotelService.getHotelByNameOrAddress(hotelName);
    }

    @GetMapping("/rating/{hotelRating}")
    public List<HotelResponse> getHotelByRating(@PathVariable("hotelRating") float hotelRating){
        return hotelService.getHotelByRating(hotelRating);
    }


    //  get all room by HotelId
    @GetMapping("/room/{id}")
    public List<RoomResponse> getAllRoom(@PathVariable("id") long id){
        return hotelService.getAllRooms(id);
    }


    // update isAvailable in room database to false
    @PutMapping("/room/reduceRoom/{id}")
    public ResponseEntity<Void> reduceRoom(@PathVariable("id") long id,@RequestParam long roomNumber){
        hotelService.reduceRoom(id,roomNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/room/updateAvailability/{hotelId}/{roomNumber}")
    public ResponseEntity<Void> updateAvailability(@PathVariable("hotelId") long hotelId, @RequestParam long roomNumber){
        hotelService.updateAvailability(hotelId,roomNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
