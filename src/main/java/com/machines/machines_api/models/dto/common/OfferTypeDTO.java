package com.machines.machines_api.models.dto.common;

import com.machines.machines_api.enums.OfferType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OfferTypeDTO extends ProductDTO {
    private OfferType offerType;
}
