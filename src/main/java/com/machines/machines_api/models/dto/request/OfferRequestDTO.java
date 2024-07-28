package com.machines.machines_api.models.dto.request;

import com.machines.machines_api.models.dto.common.OfferDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OfferRequestDTO extends OfferDTO {
    private UUID cityId;
    private UUID subcategoryId;
    private UUID mainPictureId;
    private Set<UUID> pictureIds;
}
