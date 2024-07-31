package com.machines.machines_api.models.dto.specifications;

import com.machines.machines_api.enums.OfferSaleType;
import com.machines.machines_api.enums.OfferState;
import com.machines.machines_api.models.dto.common.BaseDTO;
import lombok.*;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferSpecificationDTO extends BaseDTO {
    private String search;
    private UUID subcategoryId;
    private UUID cityId;
    private OfferState offerState;
    private OfferSaleType offerSaleType;
    private Double minPrice;
    private Double maxPrice;
    private Boolean bulgarian;

    public boolean isValidMinPrice() {
        return minPrice != null && minPrice > 0;
    }

    public boolean isValidMaxPrice() {
        return maxPrice != null && maxPrice > 0;
    }

    public boolean isBulgarian() {
        return bulgarian != null && bulgarian;
    }
}
