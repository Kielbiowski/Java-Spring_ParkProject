package com.kielbiowski.parkproject.dto;

import com.kielbiowski.parkproject.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDTO {
    private Integer id;
    private SpotDTO spotDTO;
    private CarDTO carDTO;

    @NotNull
    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;
    private Integer amount;

    public static Transaction toTransaction(TransactionDTO transactionDTO){
        return new Transaction(
                transactionDTO.getId(),
                SpotDTO.toSpot(transactionDTO.getSpotDTO()),
                CarDTO.toCar(transactionDTO.getCarDTO()),
                transactionDTO.getStartDateTime(),
                transactionDTO.getEndDateTime(),
                transactionDTO.getAmount());
    }

    public static TransactionDTO toTransactionDTO(Transaction transaction){
        return new TransactionDTO(
                transaction.getId(),
                SpotDTO.toSpotDTO(transaction.getSpot()),
                CarDTO.toCarDTO(transaction.getCar()),
                transaction.getStartDateTime(),
                transaction.getEndDateTime(),
                transaction.getAmount());
    }
}
