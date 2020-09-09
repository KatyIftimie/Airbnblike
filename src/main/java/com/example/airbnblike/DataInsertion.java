package com.example.airbnblike;

import com.example.airbnblike.amenity.model.AmenityType;
import com.example.airbnblike.amenity.repository.AmenityRepository;
import com.example.airbnblike.amenity.repository.AmenityTypeRepository;
import com.example.airbnblike.auth.model.UserType;
import com.example.airbnblike.auth.repository.UserTypeRepository;
import com.example.airbnblike.bed.repository.BedRepository;
import com.example.airbnblike.amenity.model.Amenity;
import com.example.airbnblike.bed.model.Bed;
import com.example.airbnblike.reservation.model.ReservationStatus;
import com.example.airbnblike.reservation.repository.ReservationStatusRepository;
import com.example.airbnblike.room.model.Room;
import com.example.airbnblike.rental.model.RentalType;
import com.example.airbnblike.rental.repository.*;
import com.example.airbnblike.room.model.RoomType;
import com.example.airbnblike.room.repository.RoomRepository;
import com.example.airbnblike.room.repository.RoomTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DataInsertion {

    // Access data insertion by making a GET request at 'http://localhost:8080/test/add-data'

    private final UserTypeRepository userTypeRepository;
    private final BedRepository bedRepository;
    private final AmenityTypeRepository amenityTypeRepository;
    private final AmenityRepository amenityRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final ReservationStatusRepository reservationStatusRepository;

    public void addData1(){
        // Default user types
        UserType ut1 = new UserType();
        ut1.setName(UserType.Type.ADMIN);
        ut1.setDescription("Verifies hosts, checks reviews, moderates problems, blocks users, removes spam");
        userTypeRepository.save(ut1);

        UserType ut2 = new UserType();
        ut2.setName(UserType.Type.HOST);
        ut2.setDescription("Uploads and manages one or more rentals, makes bookings");
        userTypeRepository.save(ut2);

        UserType ut3 = new UserType();
        ut3.setName(UserType.Type.GUEST);
        ut3.setDescription("Makes and manages bookings, leaves reviews");
        userTypeRepository.save(ut3);

        // Default beds
        Bed b1 = new Bed();
        b1.setType(Bed.Type.SINGLE);
        b1.setNumberOfPersons(1);
        bedRepository.save(b1);

        Bed b2 = new Bed();
        b2.setType(Bed.Type.DOUBLE);
        b2.setNumberOfPersons(2);
        bedRepository.save(b2);

        Bed b3 = new Bed();
        b3.setType(Bed.Type.KING);
        b3.setNumberOfPersons(2);
        bedRepository.save(b3);

        Bed b4 = new Bed();
        b4.setType(Bed.Type.QUEEN);
        b4.setNumberOfPersons(2);
        bedRepository.save(b4);

        Bed b5 = new Bed();
        b5.setType(Bed.Type.TWIN);
        b5.setNumberOfPersons(1);
        bedRepository.save(b5);

        // Default amenity types
        AmenityType at1 = new AmenityType();
        at1.setName(AmenityType.Type.RENTAL_AMENITY);
        amenityTypeRepository.save(at1);

        AmenityType at2 = new AmenityType();
        at2.setName(AmenityType.Type.ROOM_AMENITY);
        amenityTypeRepository.save(at2);

        // Default amenities
        Amenity a1 = new Amenity();
        a1.setName("Wifi");
        a1.setAmenityType(at1);
        amenityRepository.save(a1);

        Amenity a2 = new Amenity();
        a2.setName("Air conditioning");
        a2.setAmenityType(at2);
        amenityRepository.save(a2);

        Amenity a3 = new Amenity();
        a3.setName("Parking lot");
        a3.setAmenityType(at1);
        amenityRepository.save(a3);

        Amenity a4 = new Amenity();
        a4.setName("private bathroom");
        a4.setAmenityType(at2);
        amenityRepository.save(a4);

        Amenity a5 = new Amenity();
        a5.setName("Balcony");
        a5.setAmenityType(at2);
        amenityRepository.save(a5);

        Amenity a6 = new Amenity();
        a6.setName("Elevator");
        a6.setAmenityType(at1);
        amenityRepository.save(a6);

        Amenity a7 = new Amenity();
        a7.setName("Restaurant");
        a7.setAmenityType(at1);
        amenityRepository.save(a7);

        Amenity a8 = new Amenity();
        a8.setName("TV");
        a8.setAmenityType(at2);
        amenityRepository.save(a8);

        Amenity a9 = new Amenity();
        a9.setName("Refrigerator");
        a9.setAmenityType(at2);
        amenityRepository.save(a9);

        Amenity a10 = new Amenity();
        a10.setName("Garden or backyard");
        a10.setAmenityType(at1);
        amenityRepository.save(a10);

        // Default room types
        RoomType rt1 = new RoomType();
        rt1.setName(RoomType.Type.SINGLE);
        rt1.setCapacity(1);
        roomTypeRepository.save(rt1);

        RoomType rt2 = new RoomType();
        rt2.setName(RoomType.Type.DOUBLE);
        rt2.setCapacity(2);
        roomTypeRepository.save(rt2);

        RoomType rt3 = new RoomType();
        rt3.setName(RoomType.Type.TRIPLE);
        rt3.setCapacity(3);
        roomTypeRepository.save(rt3);

        RoomType rt4 = new RoomType();
        rt4.setName(RoomType.Type.QUAD);
        rt4.setCapacity(4);
        roomTypeRepository.save(rt4);

        RoomType rt5 = new RoomType();
        rt5.setName(RoomType.Type.SUITE);
        roomTypeRepository.save(rt5);
    }

    public void addData2() {
        ReservationStatus rs1 = new ReservationStatus();
        rs1.setName(ReservationStatus.Status.BOOKED);
        reservationStatusRepository.save(rs1);

        ReservationStatus rs2 = new ReservationStatus();
        rs2.setName(ReservationStatus.Status.CANCELED);
        reservationStatusRepository.save(rs2);

        ReservationStatus rs3 = new ReservationStatus();
        rs3.setName(ReservationStatus.Status.COMPLETED);
        reservationStatusRepository.save(rs3);

        ReservationStatus rs4 = new ReservationStatus();
        rs4.setName(ReservationStatus.Status.RESCHEDULED);
        reservationStatusRepository.save(rs4);
    }
}
