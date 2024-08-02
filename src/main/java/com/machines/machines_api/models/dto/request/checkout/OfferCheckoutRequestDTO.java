package com.machines.machines_api.models.dto.request.checkout;

import com.machines.machines_api.enums.OfferType;
import com.machines.machines_api.models.dto.common.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferCheckoutRequestDTO extends BaseCheckoutRequestDTO {
    private OfferType offerType;
    private UUID offerId;

    public OfferCheckoutRequestDTO(OfferType offerType, UUID offerId, BaseCheckoutRequestDTO baseCheckoutRequestDTO) {
        super(baseCheckoutRequestDTO.getCustomerEmail(), baseCheckoutRequestDTO.getCustomerName());
        this.offerType = offerType;
        this.offerId = offerId;
    }
}
