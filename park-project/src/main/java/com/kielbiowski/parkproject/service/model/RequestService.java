package com.kielbiowski.parkproject.service.model;

import com.kielbiowski.parkproject.dto.CarDTO;
import com.kielbiowski.parkproject.dto.RequestDTO;
import com.kielbiowski.parkproject.exception.NotFoundException;
import com.kielbiowski.parkproject.model.Request;
import com.kielbiowski.parkproject.repository.RequestRepository;
import com.kielbiowski.parkproject.service.security.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService implements ServiceInterface<RequestDTO> {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public RequestDTO findById(Integer id) {
        return RequestDTO.toRequestDTO(requestRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public RequestDTO create(RequestDTO requestDTO) {
        return RequestDTO.toRequestDTO(requestRepository.save(RequestDTO.toRequest(requestDTO)));
    }

    @Override
    public RequestDTO update(RequestDTO requestDTO) {
        Request entity = requestRepository.findById(requestDTO.getId()).orElseThrow(NotFoundException::new);
        entity.setCar(CarDTO.toCar(requestDTO.getCarDTO()));
        entity.setStartDateTime(requestDTO.getStartDateTime());
        entity.setEndDateTime(requestDTO.getEndDateTime());
        return RequestDTO.toRequestDTO(requestRepository.save(entity));
    }

    @Override
    public void delete(Integer id) {
        requestRepository.delete(requestRepository.findById(id).orElseThrow(NotFoundException::new));
    }
}
