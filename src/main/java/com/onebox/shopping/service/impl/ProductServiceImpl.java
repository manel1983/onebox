package com.onebox.shopping.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.onebox.shopping.domain.model.Product;
import com.onebox.shopping.domain.repository.ProductRepository;
import com.onebox.shopping.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	
	public ProductServiceImpl(final ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product findProduct(final Long productId) {
		return this.productRepository.findProduct(productId);
	}

	public List<Product> searchProducts(final String description) {
		return productRepository.searchProducts(description);
	}

}
