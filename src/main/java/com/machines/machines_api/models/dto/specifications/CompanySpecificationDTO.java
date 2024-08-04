package com.machines.machines_api.models.dto.specifications;

import com.machines.machines_api.enums.CompanySort;
import com.machines.machines_api.models.dto.common.BaseDTO;
import lombok.*;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanySpecificationDTO extends BaseDTO {
    private String search;
    private UUID cityId;
    private CompanySort companySort;
}
