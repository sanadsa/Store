package com.hit.customer;

import com.hit.Branch;
import com.hit.Product;
import com.hit.storeManager;

public class NewCustomer extends Customer {
    private final double salePercent = 0.3;

    public NewCustomer(String customerName, String customerId, String customerPhone, String belongName)
    {
        super(customerName, customerId, customerPhone, belongName, customerType.NewCustomer);
    }

    @Override
    public double getPrice(Product.productType product) {
        int productPrice = storeManager.product.priceOfProducts.get(product);
        return productPrice*salePercent;
    }
}
