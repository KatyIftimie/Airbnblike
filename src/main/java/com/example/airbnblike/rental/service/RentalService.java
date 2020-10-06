package com.example.airbnblike.rental.service;

import com.example.airbnblike.address.model.Address;
import com.example.airbnblike.address.service.AddressService;
import com.example.airbnblike.amenity.model.Amenity;
import com.example.airbnblike.amenity.service.AmenityService;
import com.example.airbnblike.auth.service.AuthService;
import com.example.airbnblike.image.model.Image;
import com.example.airbnblike.image.service.ImageService;
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
import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final RentalTypeRepository rentalTypeRepository;
    private final RoomService roomService;
    private final AmenityService amenityService;
    private final AddressService addressService;
    private final AuthService authService;
    private final ImageService imageService;

    @Transactional
    public List<Rental> getRentalsByCountry(String country) {
        return rentalRepository.findAllByAddress_CountryIgnoreCase(country);
    }

    @Transactional
    public Rental getRentalByID(Long ID) {
        return rentalRepository.getOne(ID);
    }

    @Transactional
    public List<RentalType> getAllRentalTypes() {
        return rentalTypeRepository.findAll();
    }

    public RentalType getRentalTypeByID(Long ID) {
        return rentalTypeRepository.getOne(ID);
    }

    public List<Room> getRentalRooms(Long ID){
        return getRentalByID(ID).getRooms();
    }

    public Rental addRental(RentalDto rentalDto) {
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

//        rentalDto.getImages().forEach(file -> {
//            Image image = new Image();
//            image.setFileName(generateUniqueImageFileName());
//            image.setOriginalFileName(file.getOriginalFilename());
//            newRental.addImage(image);
//        });

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

        savedRental.getImages().forEach(image -> {
            image.setRental(savedRental);
        });

        address.setRental(savedRental);
        addressService.save(address);

        authService.getUserByID(rentalDto.getHostUserID()).addRental(savedRental);

        Rental newSavedRental = rentalRepository.save(savedRental);
        rentalRepository.flush();
        return newSavedRental;
    }

    private String generateUniqueImageFileName() {
        String fileName;
        Image image;
        do {
            fileName = UUID.randomUUID().toString();
            image = imageService.getImageByFileName(fileName);
        } while (image != null);
        return fileName;
    }
}
