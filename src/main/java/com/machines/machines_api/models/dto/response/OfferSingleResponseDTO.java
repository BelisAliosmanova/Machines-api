package com.machines.machines_api.models.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OfferSingleResponseDTO extends OfferResponseDTO {
    private List<OfferResponseDTO> similarOffers;
}
