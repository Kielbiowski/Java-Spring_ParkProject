package com.kielbiowski.parkproject.service;

import com.kielbiowski.parkproject.dto.OfferDTO;
import com.kielbiowski.parkproject.dto.SpotDTO;
import com.kielbiowski.parkproject.exception.NotFoundException;
import com.kielbiowski.parkproject.model.Offer;
import com.kielbiowski.parkproject.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService implements ServiceInterface<OfferDTO> {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public OfferDTO findById(Integer id) {
        return OfferDTO.toOfferDTO(offerRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public OfferDTO create(OfferDTO offerDTO) {
        return OfferDTO.toOfferDTO(offerRepository.save(OfferDTO.toOffer(offerDTO)));
    }

    @Override
    public OfferDTO update(OfferDTO offerDTO) {
        Offer entity = offerRepository.findById(offerDTO.getId()).orElseThrow(NotFoundException::new);
        entity.setSpot(SpotDTO.toSpot(offerDTO.getSpotDTO()));
        entity.setStartDateTime(offerDTO.getStartDateTime());
        entity.setEndDateTime(offerDTO.getEndDateTime());
        return OfferDTO.toOfferDTO(offerRepository.save(entity));
    }

    @Override
    public void delete(Integer id) {
        offerRepository.delete(offerRepository.findById(id).orElseThrow(NotFoundException::new));
    }
}
