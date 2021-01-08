package com.kielbiowski.parkproject.service.model;

import com.kielbiowski.parkproject.dto.*;
import com.kielbiowski.parkproject.exception.NotFoundException;
import com.kielbiowski.parkproject.model.User;
import com.kielbiowski.parkproject.repository.RoleRepository;
import com.kielbiowski.parkproject.repository.UserRepository;
import com.kielbiowski.parkproject.service.security.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService implements ServiceInterface<UserDTO> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDTO findById(Integer id) {
        return UserDTO.toUserDTO(userRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    public UserDTO findByEmail(String email) {
        return UserDTO.toUserDTO(userRepository.findByEmail(email));
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        //Null-safe stream done with Java 9' Stream.ofNullable
        userDTO.setRoleDTOs(Stream.ofNullable(roleRepository.findAll())
                .flatMap(Collection::stream)
                .map(RoleDTO::toRoleDTO)
                .collect(Collectors.toList()));
        return UserDTO.toUserDTO(userRepository.save(UserDTO.toUser(userDTO)));
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        User entity = userRepository.findById(userDTO.getId()).orElseThrow(NotFoundException::new);
        entity.setEmail(userDTO.getEmail());
        entity.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        entity.setAccountBalance(userDTO.getAccountBalance());
        entity.setName(userDTO.getName());
        entity.setSurname(userDTO.getSurname());
        entity.setPhoneNumber(userDTO.getPhoneNumber());
        //Null-safe stream done with Java 9' Stream.ofNullable
        entity.setSpots(Stream.ofNullable(userDTO.getSpotDTOs())
                .flatMap(Collection::stream)
                .map(SpotDTO::toSpot)
                .collect(Collectors.toList()));
        entity.setCars(Stream.ofNullable(userDTO.getCarDTOs())
                .flatMap(Collection::stream)
                .map(CarDTO::toCar)
                .collect(Collectors.toList()));
        //Null-safe streams done with Java 8' Optionals
        entity.setParkings(Optional.ofNullable(userDTO.getParkingDTOs())
                .stream()
                .flatMap(Collection::stream)
                .map(ParkingDTO::toParking)
                .collect(Collectors.toList()));
        entity.setRoles(Optional.ofNullable(userDTO.getRoleDTOs())
                .stream()
                .flatMap(Collection::stream)
                .map(RoleDTO::toRole)
                .collect(Collectors.toList()));

        return UserDTO.toUserDTO(userRepository.save(entity));
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(NotFoundException::new));
    }
}
