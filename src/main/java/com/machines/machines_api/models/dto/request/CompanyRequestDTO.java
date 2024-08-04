package com.machines.machines_api.models.dto.request;


import com.machines.machines_api.models.dto.common.CompanyDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CompanyRequestDTO extends CompanyDTO {
    private UUID ownerId;
    private UUID cityId;
    private UUID mainPictureId;
    private Set<UUID> pictureIds;
}
