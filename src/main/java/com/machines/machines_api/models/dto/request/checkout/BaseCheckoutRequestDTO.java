package com.machines.machines_api.models.dto.request.checkout;

import com.machines.machines_api.models.dto.common.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseCheckoutRequestDTO extends BaseDTO {
    @NotBlank
    private String customerEmail;

    @NotBlank
    private String customerName;
}
