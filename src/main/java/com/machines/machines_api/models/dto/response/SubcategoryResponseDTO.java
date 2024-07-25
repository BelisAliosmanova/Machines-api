package com.machines.machines_api.models.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.machines.machines_api.models.dto.common.SubcategoryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SubcategoryResponseDTO extends SubcategoryDTO {
    private UUID id;
    private UUID categoryId;
    private LocalDateTime deletedAt;
}
