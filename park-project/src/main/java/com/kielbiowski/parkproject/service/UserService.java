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

import java.util.stream.Collectors;

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
        entity.setSpots(userDTO.getSpotDTOs()
                .stream()
                .map(SpotDTO::toSpot)
                .collect(Collectors.toList()));
        entity.setCars(userDTO.getCarDTOs()
                .stream()
                .map(CarDTO::toCar)
                .collect(Collectors.toList()));
        entity.setParkings(userDTO.getParkingDTOs()
                .stream()
                .map(ParkingDTO::toParking)
                .collect(Collectors.toList()));
        return UserDTO.toUserDTO(userRepository.save(entity));
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(NotFoundException::new));
    }
}
