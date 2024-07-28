package com.machines.machines_api.models.dto.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SubcategoryDTO extends BaseDTO {
    public String name;
}
