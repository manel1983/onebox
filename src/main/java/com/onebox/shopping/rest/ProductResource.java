package com.onebox.shopping.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onebox.shopping.domain.model.Product;
import com.onebox.shopping.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;

/**
 * REST controller for managing Products.
 */
@RestController
@Api(value = "/api/product")
@SwaggerDefinition(tags = {
	@io.swagger.annotations.Tag(name = "Products Resource", description = "Products functionalities")
})
@RequestMapping("/api")
public class ProductResource {

	private final Logger log = LoggerFactory.getLogger(ProductResource.class);

	private final ProductService productService;

	public ProductResource(final ProductService productService) {
		this.productService = productService;
	}

	/**
	 * GET /product : Create a cart.
	 *
	 */
	@ApiOperation(value = "Product", code = 201, notes = "Find a Product.", consumes = "application/json", produces = "application/json" )
	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> findProduct(@PathVariable Long productId) {

		log.debug("Find a Product");

		Product product = this.productService.findProduct(productId);

		return ResponseEntity.ok().body(product);
	}

	/**
	 * Search /product : Search products.
	 *
	 */
	@ApiOperation(value = "Product", code = 200, notes = "Search products.", consumes = "application/json", produces = "application/json")
	@GetMapping("/product")
	public ResponseEntity<List<Product>> searchProducts(@RequestParam(name = "description", required = false) String description) {
		log.debug("Search products");

		List<Product> products = this.productService.searchProducts(description);

		return ResponseEntity.ok().body(products);
	}

}
