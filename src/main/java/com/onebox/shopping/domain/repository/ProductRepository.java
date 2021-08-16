package com.onebox.shopping.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.onebox.shopping.domain.model.Product;

@Repository
public interface ProductRepository {
		
	Product findProduct(final Long productId);
	
	List<Product> searchProducts(final String description);

}
