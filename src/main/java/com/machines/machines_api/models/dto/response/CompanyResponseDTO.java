package com.machines.machines_api.models.dto.response;

import com.machines.machines_api.models.dto.auth.PublicUserDTO;
import com.machines.machines_api.models.dto.common.CompanyDTO;
import com.machines.machines_api.models.entity.File;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CompanyResponseDTO extends CompanyDTO {
    private PublicUserDTO owner;
    private CityResponseDTO city;
    private File mainPicture;
    private Set<File> pictures;
}
