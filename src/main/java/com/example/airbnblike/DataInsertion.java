package com.example.airbnblike;

import com.example.airbnblike.enums.AmenityType;
import com.example.airbnblike.enums.BedType;
import com.example.airbnblike.enums.RoomType;
import com.example.airbnblike.model.Amenity;
import com.example.airbnblike.model.Bed;
import com.example.airbnblike.model.Room;
import com.example.airbnblike.rental.model.Rental;
import com.example.airbnblike.rental.model.RentalType;
import com.example.airbnblike.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DataInsertion {

    private final AmenityRepository amenityRepository;
    private final BedRepository bedRepository;
    private final RentalTypeRepository rentalTypeRepository;
    private final RoomRepository roomRepository;


    public void addData(){
        Amenity a1=new Amenity();
        a1.setType(AmenityType.AIR_CONDITIONING);
        amenityRepository.save(a1);
        Amenity a2=new Amenity();
        a2.setType(AmenityType.BALCONY);
        amenityRepository.save(a2);
        Amenity a3=new Amenity();
        a3.setType(AmenityType.PRIVATE_BATHTUB);
        amenityRepository.save(a3);
        Amenity a4=new Amenity();
        a4.setType(AmenityType.ELEVATOR);
        amenityRepository.save(a4);
        Amenity a5=new Amenity();
        a5.setType(AmenityType.WIFI);
        amenityRepository.save(a5);
        Bed b1= new Bed();
        b1.setType(BedType.SINGLE);
        bedRepository.save(b1);
        Bed b2= new Bed();
        b2.setType(BedType.DOUBLE);
        bedRepository.save(b2);
        Bed b3= new Bed();
        b3.setType(BedType.DOUBLE_QUEEN);
        bedRepository.save(b3);
        Bed b4= new Bed();
        b4.setType(BedType.KING);
        bedRepository.save(b4);
        Bed b5= new Bed();
        b5.setType(BedType.QUEEN);
        bedRepository.save(b5);
        Room r1= new Room();
        r1.setRoomType(RoomType.DOUBLE);
        roomRepository.save(r1);
        Room r2= new Room();
        r2.setRoomType(RoomType.SINGLE);
        roomRepository.save(r2);
        RentalType re1= new RentalType();
        re1.setName("HOTEL");
        rentalTypeRepository.save(re1);
        RentalType re2= new RentalType();
        re2.setName("HOSTEL");
        rentalTypeRepository.save(re2);
        RentalType re3= new RentalType();
        re3.setName("VILLA");
        rentalTypeRepository.save(re3);
        RentalType re4= new RentalType();
        re4.setName("GUEST_HOUSE");
        rentalTypeRepository.save(re4);
        RentalType re5= new RentalType();
        re5.setName("STUDIO");
        rentalTypeRepository.save(re5);

    }
}
