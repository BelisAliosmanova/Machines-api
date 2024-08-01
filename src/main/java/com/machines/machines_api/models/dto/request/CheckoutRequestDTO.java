package com.machines.machines_api.models.dto.request;

import com.machines.machines_api.interfaces.CheckoutProduct;
import com.machines.machines_api.models.dto.common.BaseDTO;
import com.stripe.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CheckoutRequestDTO extends BaseDTO {
    @NotNull
    private List<String> checkoutIds;

    @NotBlank
    private String customerEmail;

    @NotBlank
    private String customerName;
}
