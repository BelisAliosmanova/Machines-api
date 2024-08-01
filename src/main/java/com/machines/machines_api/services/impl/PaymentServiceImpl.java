package com.machines.machines_api.services.impl;

import com.machines.machines_api.config.StripeConfig;
import com.machines.machines_api.enums.Product;
import com.machines.machines_api.models.dto.request.CheckoutRequestDTO;
import com.machines.machines_api.services.PaymentService;
import com.machines.machines_api.services.ProductService;
import com.machines.machines_api.utils.CustomerUtil;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final ProductService productService;
    private final StripeConfig stripeConfig;

    @Override
    public String createHostedCheckoutSession(CheckoutRequestDTO checkoutRequestDTO) throws StripeException {
        Stripe.apiKey = stripeConfig.getApiKey();

        Customer customer = CustomerUtil.findOrCreateCustomer(checkoutRequestDTO.getCustomerEmail(), checkoutRequestDTO.getCustomerName());

        SessionCreateParams.Builder paramsBuilder =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setCustomer(customer.getId())
                        .setSuccessUrl(stripeConfig.getSuccessUrl())
                        .setCancelUrl(stripeConfig.getCancelUrl());

        List<Product> products = checkoutRequestDTO.getCheckoutIds().stream().map(productService::getByCheckoutId).toList();

        for (Product product : products) {
            paramsBuilder.addLineItem(
                    SessionCreateParams.LineItem.builder()
                            .setQuantity(1L)
                            .setPriceData(
                                    SessionCreateParams.LineItem.PriceData.builder()
                                            .setProductData(
                                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                            .putMetadata("app_id", product.getCheckoutId())
                                                            .setName(product.getName())
                                                            .build()
                                            )
                                            .setCurrency(product.getCurrency())
                                            .setUnitAmountDecimal(product.getUnitAmountDecimal())
                                            .build())
                            .build());
        }

        Session session = Session.create(paramsBuilder.build());

        return session.getUrl();
    }
}
