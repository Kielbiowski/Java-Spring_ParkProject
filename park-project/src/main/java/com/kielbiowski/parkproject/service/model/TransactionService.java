package com.kielbiowski.parkproject.service.model;

import com.kielbiowski.parkproject.dto.CarDTO;
import com.kielbiowski.parkproject.dto.SpotDTO;
import com.kielbiowski.parkproject.dto.TransactionDTO;
import com.kielbiowski.parkproject.exception.NotFoundException;
import com.kielbiowski.parkproject.model.Transaction;
import com.kielbiowski.parkproject.repository.TransactionRepository;
import com.kielbiowski.parkproject.service.security.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements ServiceInterface<TransactionDTO> {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDTO findById(Integer id) {
        return TransactionDTO.toTransactionDTO(transactionRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public TransactionDTO create(TransactionDTO transactionDTO) {
        return TransactionDTO.toTransactionDTO(transactionRepository.save(TransactionDTO.toTransaction(transactionDTO)));
    }

    @Override
    public TransactionDTO update(TransactionDTO transactionDTO) {
        Transaction entity = transactionRepository.findById(transactionDTO.getId()).orElseThrow(NotFoundException::new);
        entity.setSpot(SpotDTO.toSpot(transactionDTO.getSpotDTO()));
        entity.setCar(CarDTO.toCar(transactionDTO.getCarDTO()));
        entity.setStartDateTime(transactionDTO.getStartDateTime());
        entity.setEndDateTime(transactionDTO.getEndDateTime());
        entity.setAmount(transactionDTO.getAmount());
        return TransactionDTO.toTransactionDTO(transactionRepository.save(entity));
    }

    @Override
    public void delete(Integer id) {
        transactionRepository.delete(transactionRepository.findById(id).orElseThrow(NotFoundException::new));
    }
}
