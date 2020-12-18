package com.kielbiowski.parkproject.dto;

import com.kielbiowski.parkproject.model.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestDTO {
    private Integer id;
    private CarDTO carDTO;

    @NotNull
    private LocalDateTime startDateTime;

    @NotNull
    private LocalDateTime endDateTime;

    public static Request toRequest (RequestDTO requestDTO){
        return new Request(
                requestDTO.getId(),
                CarDTO.toCar(requestDTO.getCarDTO()),
                requestDTO.getStartDateTime(),
                requestDTO.getEndDateTime());
    }

    public static RequestDTO toRequestDTO (Request request){
        return new RequestDTO(
                request.getId(),
                CarDTO.toCarDTO(request.getCar()),
                request.getStartDateTime(),
                request.getEndDateTime());
    }
}
