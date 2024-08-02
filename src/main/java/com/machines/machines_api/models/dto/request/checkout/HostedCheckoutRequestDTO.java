package com.machines.machines_api.models.dto.request.checkout;

import com.machines.machines_api.interfaces.CheckoutProduct;
import com.machines.machines_api.models.dto.common.BaseDTO;
import com.stripe.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HostedCheckoutRequestDTO extends BaseCheckoutRequestDTO {
    // List of checkoutId to metaData maps
    @NotNull
    private Map<String, Map<String, String>> checkoutIdsMap;

    public HostedCheckoutRequestDTO(Map<String, Map<String, String>> checkoutIds, BaseCheckoutRequestDTO baseCheckoutRequestDTO) {
        super(baseCheckoutRequestDTO.getCustomerEmail(), baseCheckoutRequestDTO.getCustomerName());
        this.checkoutIdsMap = checkoutIds;
    }
}
