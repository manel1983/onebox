package com.onebox.shopping.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onebox.shopping.rest.mapper.ProductMapper;
import com.onebox.shopping.rest.model.Product;
import com.onebox.shopping.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST controller for managing Products.
 */
@RestController("/api")
@Api(tags = { "Product Resource" })
@RequestMapping("/api")
public class ProductResource {

	private final Logger log = LoggerFactory.getLogger(ProductResource.class);

	private final ProductService productService;

	private final ProductMapper productMapper;

	public ProductResource(final ProductService productService, final ProductMapper productMapper) {
		this.productService = productService;
		this.productMapper = productMapper;
	}

	/**
	 * GET /product : Create a cart.
	 *
	 */
	@ApiOperation(value = "Product", code = 201, notes = "Find a Product.")
	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> findProduct(@PathVariable Long productId) {

		log.debug("Find a Product");

		Product product = this.productMapper.mapEntity(this.productService.findProduct(productId));

		return ResponseEntity.ok().body(product);
	}

	/**
	 * Search /product : Search products.
	 *
	 */
	@ApiOperation(value = "Product", code = 200, notes = "Search products.")
	@GetMapping("/product")
	public ResponseEntity<List<Product>> searchProducts(@PathVariable String description) {
		log.debug("Search products");

		List<Product> products = this.productMapper.mapList(this.productService.searchProducts(description));

		return ResponseEntity.ok().body(products);
	}

}
