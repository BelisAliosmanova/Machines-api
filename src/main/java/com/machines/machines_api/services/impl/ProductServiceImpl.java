package com.machines.machines_api.services.impl;

import com.machines.machines_api.enums.Product;
import com.machines.machines_api.interfaces.CheckoutProduct;
import com.machines.machines_api.repositories.ProductRepository;
import com.machines.machines_api.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product getByCheckoutId(String checkoutId) {
        Optional<Product> product = productRepository.findByCheckoutIdAndDeletedAtIsNull(checkoutId);

        if (product.isEmpty()) {
            throw new RuntimeException("My exception");
        }

        return product.get();
    }

    @Override
    public List<Product> saveAllIfNotAdded(List<CheckoutProduct> checkoutProducts) {
        List<Product> products = new ArrayList<>();

        for (CheckoutProduct checkoutProduct : checkoutProducts) {
            Optional<Product> product = productRepository.findByCheckoutIdAndDeletedAtIsNull(checkoutProduct.checkoutId);

            if (product.isEmpty()) {
                products.add(toProduct(checkoutProduct));
            }
        }

        return productRepository.saveAll(products);
    }

    private Product toProduct(CheckoutProduct checkoutProduct) {
        Product product = new Product();
        product.setName(checkoutProduct.name);
        product.setCheckoutId(checkoutProduct.checkoutId);
        product.setCurrency(checkoutProduct.currency);
        product.setUnitAmountDecimal(checkoutProduct.unitAmountDecimal);

        return product;
    }
}
