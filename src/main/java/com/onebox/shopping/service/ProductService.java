package com.onebox.shopping.service;

import java.util.List;

import com.onebox.shopping.domain.model.ProductDb;

public interface ProductService {

	ProductDb findProduct(final Long productId);

	List<ProductDb> searchProducts(final String description);

}
