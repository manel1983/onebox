package com.onebox.shopping.service;

import java.util.List;

import com.onebox.shopping.rest.model.Product;

public interface ProductService {

	Product findProduct(final Long productId);

	List<Product> searchProducts(final String description);

}
