package com.onebox.shopping.domain.repository.cache;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.onebox.shopping.domain.model.ProductDb;
import com.onebox.shopping.domain.repository.ProductRepository;

@Component
public class ProductRepositoryCache implements ProductRepository {
	
	public Optional<ProductDb> findProduct(final Long productId) {
		List<ProductDb> products =  cachedList();
		return products.stream().filter(pr -> pr.getId().equals(productId)).findAny();
	}

	public List<ProductDb> searchProducts(final String description) {
		List<ProductDb> products =  cachedList();

		return products.stream().filter(pr -> pr.getDescripton().contains(description)).collect(Collectors.toList());
	}

	@Cacheable("products")
	private List<ProductDb> cachedList() {
		List<ProductDb> products =  new ArrayList<>();

		ProductDb product = new ProductDb();
		product.setId(Long.valueOf("1"));
		product.setDescripton("Product 1");
		product.setAmount(BigDecimal.valueOf(Double.valueOf("10.50")));
		products.add(product);

		product = new ProductDb();
		product.setId(Long.valueOf("2"));
		product.setDescripton("Ticket 2");
		product.setAmount(BigDecimal.valueOf(Double.valueOf("45.90")));
		products.add(product);

		product = new ProductDb();
		product.setId(Long.valueOf("3"));
		product.setDescripton("Laptop 3");
		product.setAmount(BigDecimal.valueOf(Double.valueOf("599.00")));
		products.add(product);
		
		return products;
	}
	
}
