package com.onebox.shopping.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.onebox.shopping.domain.model.ProductDb;

@Repository
public interface ProductRepository {
		
	Optional<ProductDb> findProduct(final Long productId);
	
	List<ProductDb> searchProducts(final String description);

}
