package com.machines.machines_api.services;

import com.machines.machines_api.interfaces.CheckoutProduct;
import com.machines.machines_api.models.entity.Product;

import java.util.List;

public interface ProductService {
    Product getByCheckoutId(String checkoutId);

    void saveAllIfNotAdded(List<CheckoutProduct> checkoutProducts);
}
