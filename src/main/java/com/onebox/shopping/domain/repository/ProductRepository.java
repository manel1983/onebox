package com.onebox.shopping.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.onebox.shopping.rest.model.Product;

@Repository
public interface ProductRepository {
		
	Optional<Product> findProduct(final Long productId);
	
	List<Product> searchProducts(final String description);

}
