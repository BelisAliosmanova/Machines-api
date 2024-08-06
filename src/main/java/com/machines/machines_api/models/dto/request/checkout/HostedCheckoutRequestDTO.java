package com.machines.machines_api.models.dto.request.checkout;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
