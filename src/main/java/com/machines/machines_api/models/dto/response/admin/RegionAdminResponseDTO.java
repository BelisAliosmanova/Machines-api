package com.machines.machines_api.models.dto.response.admin;

import com.machines.machines_api.models.dto.response.RegionResponseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RegionAdminResponseDTO extends RegionResponseDTO {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
