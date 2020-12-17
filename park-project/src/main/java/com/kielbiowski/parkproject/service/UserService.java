package com.kielbiowski.parkproject.service;

import com.kielbiowski.parkproject.dto.UserDTO;
import com.kielbiowski.parkproject.exception.NotFoundException;
import com.kielbiowski.parkproject.model.User;
import com.kielbiowski.parkproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO findById(Integer id) {
        return UserDTO.toUserDTO(userRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    public List<UserDTO> findAll(){
        return userRepository.findAll().stream().map(UserDTO::toUserDTO).collect(Collectors.toList());
    }

    @Transactional
    public UserDTO create(UserDTO userDTO) {
        return UserDTO.toUserDTO(userRepository.save(UserDTO.toUser(userDTO)));
    }

    public UserDTO update(UserDTO userDTO) {
        User entity = userRepository.findById(userDTO.getId()).orElseThrow(NotFoundException::new);
        entity.setEmail(userDTO.getEmail());
        entity.setPassword(userDTO.getPassword());
        entity.setAccountBalance(userDTO.getAccountBalance());
        entity.setName(userDTO.getName());
        entity.setSurname(userDTO.getSurname());
        entity.setPhoneNumber(userDTO.getPhoneNumber());
        entity.setSpots(userDTO.getSpots());
        entity.setCars(userDTO.getCars());
        entity.setParkings(userDTO.getParkings());
        return UserDTO.toUserDTO(userRepository.save(entity));
    }

    public void delete(Integer id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(NotFoundException::new));
    }
}
