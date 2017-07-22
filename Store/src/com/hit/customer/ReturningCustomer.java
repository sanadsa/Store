package com.hit.customer;

import com.hit.Branch;
import com.hit.Product;
import com.hit.storeManager;

public class ReturningCustomer extends Customer {
    private final double salePercent = 0.1;

    public ReturningCustomer(String customerName, String customerId, String customerPhone, String belongName)
    {
        super(customerName, customerId, customerPhone, belongName, customerType.Returning);
    }

    @Override
    public double getPrice(Product.productType product) {
        int productPrice = storeManager.product.priceOfProducts.get(product);
        return productPrice*salePercent;
    }
}
