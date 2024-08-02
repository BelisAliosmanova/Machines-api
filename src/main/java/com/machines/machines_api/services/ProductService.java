package com.machines.machines_api.services;

import com.machines.machines_api.models.entity.Product;
import com.machines.machines_api.interfaces.CheckoutProduct;

import java.util.List;

public interface ProductService {
    Product getByCheckoutId(String checkoutId);

    void saveAllIfNotAdded(List<CheckoutProduct> checkoutProducts);
}
