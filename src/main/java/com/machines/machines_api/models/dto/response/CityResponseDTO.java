package com.machines.machines_api.models.dto.response;

import com.machines.machines_api.models.dto.common.CityDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CityResponseDTO extends CityDTO {
    private UUID id;
    private UUID regionId;
    private LocalDateTime deletedAt;
}
