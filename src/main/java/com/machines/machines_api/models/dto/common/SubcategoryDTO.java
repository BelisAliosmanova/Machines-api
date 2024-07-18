package com.machines.machines_api.models.dto.common;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubcategoryDTO {
    public String name;
    private LocalDateTime deletedAt;
}
