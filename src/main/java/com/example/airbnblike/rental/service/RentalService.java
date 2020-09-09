package com.example.airbnblike.rental.service;

import com.example.airbnblike.address.model.Address;
import com.example.airbnblike.address.service.AddressService;
import com.example.airbnblike.amenity.model.Amenity;
import com.example.airbnblike.amenity.service.AmenityService;
import com.example.airbnblike.auth.service.AuthService;
import com.example.airbnblike.rental.dto.RentalDto;
import com.example.airbnblike.rental.model.Rental;
import com.example.airbnblike.rental.model.RentalType;
import com.example.airbnblike.rental.repository.RentalRepository;
import com.example.airbnblike.rental.repository.RentalTypeRepository;
import com.example.airbnblike.room.model.Room;
import com.example.airbnblike.room.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.List;

@Service
@AllArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final RentalTypeRepository rentalTypeRepository;
    private final RoomService roomService;
    private final AmenityService amenityService;
    private final AddressService addressService;
    private final AuthService authService;

    @Transactional
    public List<Rental> getRentalsByCountry(String country) {
        return rentalRepository.findAllByAddress_CountryIgnoreCase(country);
    }

    @Transactional
    public List<RentalType> getAllRentalTypes() {
        return rentalTypeRepository.findAll();
    }

    public RentalType getRentalTypeByID(Long ID) {
        return rentalTypeRepository.getOne(ID);
    }

    public void addRental(RentalDto rentalDto) {
        Rental newRental = new Rental();
        newRental.setName(rentalDto.getName());
        newRental.setDescription(rentalDto.getDescription());
        newRental.setCheckInTime(rentalDto.getCheckInTime());
        newRental.setCheckOutTime(rentalDto.getCheckOutTime());

        rentalDto.getRoomsDtoList().forEach(roomDto -> {
            Room room = roomService.createModelFromDto(new Room(), roomDto);
            roomService.save(room);
            newRental.addRoom(room);
        });

        rentalDto.getAmenitiesIDs().forEach(ID -> {
            Amenity amenity = amenityService.getAmenityByID(ID);
            newRental.addAmenity(amenity);
        });

        Address address = addressService.createModelFromDto(new Address(), rentalDto.getAddressDto());
        addressService.save(address);
        newRental.setAddress(address);

        newRental.setType(getRentalTypeByID(rentalDto.getRentalTypeID()));
        newRental.setHostUser(authService.getUserByID(rentalDto.getHostUserID()));

        Rental savedRental = rentalRepository.save(newRental);
        rentalRepository.flush();

        savedRental.getRooms().forEach(room -> {
            room.setRental(savedRental);
        });

        address.setRental(newRental);
        addressService.save(address);

        rentalRepository.save(savedRental);
    }
}
