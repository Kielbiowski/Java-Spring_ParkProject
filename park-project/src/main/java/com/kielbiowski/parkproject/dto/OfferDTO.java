package com.kielbiowski.parkproject.dto;

import com.kielbiowski.parkproject.model.Offer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OfferDTO {
    private Integer id;
    private SpotDTO spotDTO;

    @NotNull
    private LocalDateTime startDateTime;

    @NotNull
    private LocalDateTime endDateTime;

    public static Offer toOffer(OfferDTO offerDTO){
        return new Offer(
                offerDTO.getId(),
                SpotDTO.toSpot(offerDTO.getSpotDTO()),
                offerDTO.getStartDateTime(),
                offerDTO.getEndDateTime());
    }

    public static OfferDTO toOfferDTO(Offer offer){
        return new OfferDTO(
                offer.getId(),
                SpotDTO.toSpotDTO(offer.getSpot()),
                offer.getStartDateTime(),
                offer.getEndDateTime());
    }
}
