package com.onebox.shopping.domain.repository.cache;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.onebox.shopping.domain.model.Product;
import com.onebox.shopping.domain.repository.ProductRepository;

@Component
public class ProductRepositoryCache implements ProductRepository {
	
	public Product findProduct(final Long productId) {
		List<Product> products =  cachedList();
		Optional<Product> optProduct = products.stream().filter(pr -> pr.getId().equals(productId)).findAny();
		return optProduct.isPresent() ? optProduct.get() : null;
	}

	public List<Product> searchProducts(final String description) {
		List<Product> products =  cachedList();

		if (description != null) {
			return products.stream().filter(pr -> pr.getDescription().contains(description)).collect(Collectors.toList());
		} else {
			return products;
		}
	}

	@Cacheable("products")
	private List<Product> cachedList() {
		List<Product> products =  new ArrayList<>();

		Product product = new Product();
		product.setId(Long.valueOf("1"));
		product.setDescription("Product 1");
		product.setAmount(BigDecimal.valueOf(Double.valueOf("10.50")));
		products.add(product);

		product = new Product();
		product.setId(Long.valueOf("2"));
		product.setDescription("Ticket 2");
		product.setAmount(BigDecimal.valueOf(Double.valueOf("45.90")));
		products.add(product);

		product = new Product();
		product.setId(Long.valueOf("3"));
		product.setDescription("Laptop 3");
		product.setAmount(BigDecimal.valueOf(Double.valueOf("599.00")));
		products.add(product);
		
		return products;
	}
	
}
