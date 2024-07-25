package com.machines.machines_api.models.dto.response.admin;

import com.machines.machines_api.models.dto.response.CategoryResponseDTO;
import com.machines.machines_api.models.dto.response.SubcategoryResponseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CategoryAdminResponseDTO extends CategoryResponseDTO {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
