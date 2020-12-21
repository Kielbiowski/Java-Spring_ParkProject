package com.kielbiowski.parkproject.service;

import com.kielbiowski.parkproject.dto.*;
import com.kielbiowski.parkproject.exception.NotFoundException;
import com.kielbiowski.parkproject.model.Spot;
import com.kielbiowski.parkproject.repository.SpotRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SpotService implements ServiceInterface<SpotDTO> {

    private final SpotRepository spotRepository;

    public SpotService(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    @Override
    public SpotDTO findById(Integer id) {
        return SpotDTO.toSpotDTO(spotRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public SpotDTO create(SpotDTO spotDTO) {
        return SpotDTO.toSpotDTO(spotRepository.save(SpotDTO.toSpot(spotDTO)));
    }

    @Override
    public SpotDTO update(SpotDTO spotDTO) {
        Spot entity = spotRepository.findById(spotDTO.getId()).orElseThrow(NotFoundException::new);
        entity.setUser(UserDTO.toUser(spotDTO.getUserDTO()));
        entity.setParking(ParkingDTO.toParking(spotDTO.getParkingDTO()));
        entity.setNumber(spotDTO.getNumber());
        entity.setSize(spotDTO.getSize());
        entity.setCar(CarDTO.toCar(spotDTO.getCarDTO()));
        //Null-safe stream done with Java 9' Stream.ofNullable
        entity.setOffers(Stream.ofNullable(spotDTO.getOfferDTOs())
                .flatMap(Collection::stream)
                .map(OfferDTO::toOffer)
                .collect(Collectors.toList()));
        //Null-safe streams done with Java 8' Optionals
        entity.setTransactions(Optional.ofNullable(spotDTO.getTransactionDTOs())
                .stream()
                .flatMap(Collection::stream)
                .map(TransactionDTO::toTransaction)
                .collect(Collectors.toList()));
        return SpotDTO.toSpotDTO(spotRepository.save(entity));
    }

    @Override
    public void delete(Integer id) {
        spotRepository.delete(spotRepository.findById(id).orElseThrow(NotFoundException::new));
    }
}
