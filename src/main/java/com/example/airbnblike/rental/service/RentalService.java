package com.example.airbnblike.rental.service;

import com.example.airbnblike.address.model.Address;
import com.example.airbnblike.address.service.AddressService;
import com.example.airbnblike.amenity.model.Amenity;
import com.example.airbnblike.amenity.service.AmenityService;
import com.example.airbnblike.auth.service.AuthService;
import com.example.airbnblike.aws.service.AWSS3Service;
import com.example.airbnblike.image.dto.UploadImagesRequest;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@AllArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final RentalTypeRepository rentalTypeRepository;
    private final RoomService roomService;
    private final AmenityService amenityService;
    private final AddressService addressService;
    private final AuthService authService;
    private final AWSS3Service s3Service;

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

    public Map<String, Long> addRental(RentalDto rentalDto) {
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

        address.setRental(savedRental);
        addressService.save(address);

        authService.getUserByID(rentalDto.getHostUserID()).addRental(savedRental);

        Rental newSavedRental = rentalRepository.save(savedRental);
        rentalRepository.flush();

        Map<String, Long> response = new HashMap<>();
        response.put("new_rental_id", newSavedRental.getId());
        return response;
    }

    public void uploadImagesForRental(Long rentalID, UploadImagesRequest request) {
        List<MultipartFile> files = Arrays.asList(request.getFile1(), request.getFile2(), request.getFile3(), request.getFile4(), request.getFile5());
        int index = 0;
        for (MultipartFile file : files) {
            if (file != null) {
                String fileName = String.format("rental-%s-%s", rentalID, index);
                s3Service.uploadFile(file, fileName, "rentals");
            }
            index += 1;
        }
    }

    public Integer countAllRentals() {
        return rentalRepository.countAllBy();
    }
}
