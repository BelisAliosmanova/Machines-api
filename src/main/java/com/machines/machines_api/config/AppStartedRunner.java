package com.machines.machines_api.config;

import com.machines.machines_api.enums.OfferType;
import com.machines.machines_api.interfaces.CheckoutProduct;
import com.machines.machines_api.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AppStartedRunner implements ApplicationRunner {
    private final ProductService productService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<CheckoutProduct> checkoutProducts = OfferType.getOfferCheckoutProducts();
        productService.saveAllIfNotAdded(checkoutProducts);
    }
}
