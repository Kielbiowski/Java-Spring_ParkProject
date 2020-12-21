package com.kielbiowski.parkproject.service;

import com.kielbiowski.parkproject.dto.CarDTO;
import com.kielbiowski.parkproject.dto.ParkingDTO;
import com.kielbiowski.parkproject.dto.SpotDTO;
import com.kielbiowski.parkproject.dto.UserDTO;
import com.kielbiowski.parkproject.exception.NotFoundException;
import com.kielbiowski.parkproject.model.User;
import com.kielbiowski.parkproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService implements ServiceInterface<UserDTO> {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO findById(Integer id) {
        return UserDTO.toUserDTO(userRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        return UserDTO.toUserDTO(userRepository.save(UserDTO.toUser(userDTO)));
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        User entity = userRepository.findById(userDTO.getId()).orElseThrow(NotFoundException::new);
        entity.setEmail(userDTO.getEmail());
        entity.setPassword(userDTO.getPassword());
        entity.setAccountBalance(userDTO.getAccountBalance());
        entity.setName(userDTO.getName());
        entity.setSurname(userDTO.getSurname());
        entity.setPhoneNumber(userDTO.getPhoneNumber());
        //Null-safe stream done with Java 9' Stream.ofNullable
        entity.setSpots(Stream.ofNullable(userDTO.getSpotDTOs())
                .flatMap(Collection::stream)
                .map(SpotDTO::toSpot)
                .collect(Collectors.toList()));
        //Null-safe streams done with Java 8' Optionals
        entity.setCars(Optional.ofNullable(userDTO.getCarDTOs())
                .stream()
                .flatMap(Collection::stream)
                .map(CarDTO::toCar)
                .collect(Collectors.toList()));
        entity.setParkings(Optional.ofNullable(userDTO.getParkingDTOs())
                .stream()
                .flatMap(Collection::stream)
                .map(ParkingDTO::toParking)
                .collect(Collectors.toList()));
        return UserDTO.toUserDTO(userRepository.save(entity));
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(NotFoundException::new));
    }
}
