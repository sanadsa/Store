package com.hit.customer;

import com.hit.Branch;
import com.hit.Product;
import com.hit.storeManager;

import java.util.ArrayList;
import java.util.List;

public class VIPCustomer extends Customer
{
    private final double salePercent = 0.5;

    public VIPCustomer(String customerName, String customerId, String customerPhone, String branchName)
    {
        super(customerName, customerId, customerPhone, branchName, customerType.VIPCustomer);
    }

    @Override
    public double getPrice(Product.productType product) {
        int productPrice = storeManager.product.priceOfProducts.get(product);
        return productPrice*salePercent;
    }
}
