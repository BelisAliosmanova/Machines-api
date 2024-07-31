package com.machines.machines_api.models.dto.specifications;

import com.machines.machines_api.enums.OfferSaleType;
import com.machines.machines_api.enums.OfferState;
import com.machines.machines_api.models.dto.common.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OfferSpecificationDTO extends BaseDTO {
    private String search;
    private UUID subcategoryId;
    private UUID cityId;
    private OfferState offerState;
    private OfferSaleType offerSaleType;
    private double minPrice;
    private double maxPrice;
    private boolean bulgarian;
}
