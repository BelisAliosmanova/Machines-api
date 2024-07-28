package com.machines.machines_api.models.dto.response;

import com.machines.machines_api.models.dto.common.OfferDTO;
import com.machines.machines_api.models.entity.File;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OfferResponseDTO extends OfferDTO {
    private CityResponseDTO city;
    private SubcategoryResponseDTO subcategory;
    private File mainPicture;
    private Set<File> pictures;
}
