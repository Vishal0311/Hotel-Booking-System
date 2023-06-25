package com.vishal.hotel.Hotel.service;

import com.vishal.hotel.Hotel.entity.Hotel;
import com.vishal.hotel.Hotel.entity.IsAvailable;
import com.vishal.hotel.Hotel.entity.Room;
import com.vishal.hotel.Hotel.exceptions.HotelServiceCustomException;
import com.vishal.hotel.Hotel.functions.Functions;
import com.vishal.hotel.Hotel.model.HotelRequest;
import com.vishal.hotel.Hotel.model.HotelResponse;
import com.vishal.hotel.Hotel.model.RoomRequest;
import com.vishal.hotel.Hotel.model.RoomResponse;
import com.vishal.hotel.Hotel.repository.HotelRepository;
import com.vishal.hotel.Hotel.repository.RoomRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.vishal.hotel.Hotel.entity.IsAvailable.False;
import static com.vishal.hotel.Hotel.entity.IsAvailable.True;
import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Log4j2
public class HotelServiceImpl implements HotelService{



    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public long addHotelAndRooms(HotelRequest hotelRequest, List<RoomRequest> roomRequests) {

        log.info("Adding room");

        List<Room> rooms = new ArrayList<>();

        for(RoomRequest roomRequest:roomRequests){
            Room room = Room.builder()
                    .roomPrice(roomRequest.getRoomPrice())
                    .roomType(roomRequest.getRoomType())
                    .roomAllottedId(roomRequest.getRoomAllottedId())
                    .roomNumber(roomRequest.getRoomNumber())
                    .isAvailable(roomRequest.getIsAvailable())
                    .build();
            rooms.add(room);

        }

        log.info("Rooms Added");

        log.info("Adding hotel...");

        Hotel hotel = Hotel.builder()
                .hotelPhoneNumber(hotelRequest.getHotelPhoneNumber())
                .hotelAddress(hotelRequest.getHotelAddress())
                .hotelRating(hotelRequest.getHotelRating())
                .hotelName(hotelRequest.getHotelName())
                .hotelRooms(rooms)
                .hotelTotalRooms(hotelRequest.getHotelTotalRooms())
                .build();



        hotelRepository.save(hotel);

        log.info("Hotel added");

        for (Room room : rooms) {
            room.setHotel(hotel);
            roomRepository.save(room);
        }


        return hotel.getHotelId();
    }



    @Override
    public List<HotelResponse> getAllHotels() {

        log.info("Getting Hotels");

        List<Hotel> hotels = hotelRepository.findAll();
        List<HotelResponse> hotelResponses = new ArrayList<>();

        for(Hotel hotel: hotels){
            HotelResponse hotelResponse = new HotelResponse();
            hotelResponse.setHotelName(hotel.getHotelName());
            hotelResponse.setHotelAddress(hotel.getHotelAddress());
            hotelResponse.setHotelRating(hotel.getHotelRating());
            hotelResponse.setHotelTotalRooms(hotel.getHotelTotalRooms());
            hotelResponse.setHotelPhoneNumber(hotel.getHotelPhoneNumber());
            hotelResponses.add(hotelResponse);
        }



        log.info("Done getting hotels");


        return hotelResponses;
    }

    @Override
    public List<HotelResponse> getHotelByNameOrAddress(String hotelName) {

        Functions functions = new Functions();


        hotelName = functions.removeSpace(hotelName).toLowerCase();


        log.info("Get the detail of Hotel: {}", hotelName);

        List<Hotel> hotelList = hotelRepository.findAll();

        List<Long>ids = new ArrayList<>();

        for(Hotel hotel:hotelList){
            if(functions.removeSpace(hotel.getHotelName()).toLowerCase().equals(hotelName)){
                ids.add(hotel.getHotelId());
            }
        }

        List<HotelResponse> hotelResponses = new ArrayList<>();

        for(long id:ids){
            Hotel hotel = hotelRepository.findById(id).orElseThrow(
                    ()-> new HotelServiceCustomException("HOTEL WITH GIVEN ATTRIBUTE NOT FOUND","HOTEL_NOT_FOUND")
            );
            HotelResponse hotelResponse = new HotelResponse();
            copyProperties(hotel,hotelResponse);
            hotelResponses.add(hotelResponse);
        }

        return hotelResponses;

    }


    @Override
    public List<HotelResponse> getHotelByRating(float hotelRating) {
        log.info("Get the detail of Hotel above rating: {}", hotelRating);

        List<Hotel> hotelList = hotelRepository.findAll();

        List<Long>ids = new ArrayList<>();

        for(Hotel hotel:hotelList){
            if(hotel.getHotelRating()>=hotelRating){
                ids.add(hotel.getHotelId());
            }
        }

        List<HotelResponse> hotelResponses = new ArrayList<>();

        for(long id:ids){
            Hotel hotel = hotelRepository.findById(id).orElseThrow(
                    ()-> new HotelServiceCustomException("RATING SHOULD BE IN THE RANGE 0 TO 5","ILLEGAL RATING")
            );
            HotelResponse hotelResponse = new HotelResponse();
            copyProperties(hotel,hotelResponse);
            hotelResponses.add(hotelResponse);
        }

        return hotelResponses;

    }

    @Override
    public List<RoomResponse> getAllRooms(long id) {

        Hotel hotel = hotelRepository.findById(id).orElseThrow(
                ()-> new HotelServiceCustomException("Hotel with given id is not available","HOTEL_NOT_FOUND")
        );

        List<Room> rooms = hotel.getHotelRooms();
        log.info("hotels and rooms: "+rooms);

        List<RoomResponse> roomResponses = new ArrayList<>();
        for(Room room: rooms){
            RoomResponse roomResponse = new RoomResponse();
            roomResponse.setRoomPrice(room.getRoomPrice());
            roomResponse.setRoomType(room.getRoomType());
            roomResponse.setIsAvailable(room.getIsAvailable());
            roomResponse.setRoomNumber(room.getRoomNumber());

            roomResponses.add(roomResponse);
        }


        return roomResponses;
    }

    @Override
    public void reduceRoom(long id, long roomNumber) {
        log.info("ID: "+id);
        log.info("roomNumber: "+roomNumber);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(
                ()-> new HotelServiceCustomException("Hotel with given id is not available","HOTEL_NOT_FOUND")
        );

        log.info("Hotel to update: "+hotel);

        for(int i=0;i<hotel.getHotelRooms().size();i++){
            if(hotel.getHotelRooms().get(i).getRoomNumber()==roomNumber){
                log.info("Room Number: "+roomNumber);
                log.info("Is Available: "+hotel.getHotelRooms().get(i).getIsAvailable());
                if(hotel.getHotelRooms().get(i).getIsAvailable()==False){
                    throw new HotelServiceCustomException("ROOM ALREADY BOOKED","ROOM_ALREADY_BOOKED");
                }
                else{
                    log.info("Updating room availability");
                    hotel.getHotelRooms().get(i).setIsAvailable(False);
                    hotelRepository.save(hotel);
                    log.info("Room availability updated");
                }
            }
        }



    }

    @Override
    public void updateAvailability(long id, long roomNumber) {
        log.info("ID: "+id);
        log.info("roomNumber: "+roomNumber);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(
                ()-> new HotelServiceCustomException("Hotel with given id is not available","HOTEL_NOT_FOUND")
        );

        log.info("Hotel to update: "+hotel);

        for(int i=0;i<hotel.getHotelRooms().size();i++){
            if(hotel.getHotelRooms().get(i).getRoomNumber()==roomNumber){
                log.info("Room Number: "+roomNumber);
                log.info("Is Available: "+hotel.getHotelRooms().get(i).getIsAvailable());
                if(hotel.getHotelRooms().get(i).getIsAvailable()==False){
                    log.info("Updating room availability");
                    hotel.getHotelRooms().get(i).setIsAvailable(True);
                    hotelRepository.save(hotel);
                    log.info("Room availability updated");
                }

            }
        }


    }

}
