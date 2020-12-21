package com.kielbiowski.parkproject.service;

import com.kielbiowski.parkproject.dto.*;
import com.kielbiowski.parkproject.exception.NotFoundException;
import com.kielbiowski.parkproject.model.Car;
import com.kielbiowski.parkproject.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CarService implements ServiceInterface<CarDTO> {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public CarDTO findById(Integer id) {
        return CarDTO.toCarDTO(carRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public CarDTO create(CarDTO carDTO) {
        return CarDTO.toCarDTO(carRepository.save(CarDTO.toCar(carDTO)));
    }

    @Override
    public CarDTO update(CarDTO carDTO) {
        Car entity = carRepository.findById(carDTO.getId()).orElseThrow(NotFoundException::new);
        entity.setUser(UserDTO.toUser(carDTO.getUserDTO()));
        entity.setSpot(SpotDTO.toSpot(carDTO.getSpotDTO()));
        entity.setNumberPlate(carDTO.getNumberPlate());
        entity.setColor(carDTO.getColor());
        entity.setBrand(carDTO.getBrand());
        entity.setModel(carDTO.getModel());
        entity.setSize(carDTO.getSize());
        entity.setRequests(carDTO.getRequestDTOs()
                .stream()
                .map(RequestDTO::toRequest)
                .collect(Collectors.toList()));
        entity.setTransactions(carDTO.getTransactionDTOs()
                .stream()
                .map(TransactionDTO::toTransaction)
                .collect(Collectors.toList()));
        return CarDTO.toCarDTO(carRepository.save(entity));
    }

    @Override
    public void delete(Integer id) {
        carRepository.delete(carRepository.findById(id).orElseThrow(NotFoundException::new));
    }
}
