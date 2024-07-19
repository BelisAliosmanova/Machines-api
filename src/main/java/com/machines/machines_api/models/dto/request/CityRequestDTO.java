package com.machines.machines_api.models.dto.request;

import com.machines.machines_api.models.dto.common.CityDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CityRequestDTO extends CityDTO {
    private UUID regionId;
}
