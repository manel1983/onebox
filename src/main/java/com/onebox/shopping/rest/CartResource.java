package com.onebox.shopping.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onebox.shopping.rest.mapper.CartMapper;
import com.onebox.shopping.rest.model.Cart;
import com.onebox.shopping.service.CartService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST controller for managing Carts.
 */
@RestController("/api")
@Api(tags = { "Cart Resource" })
@RequestMapping("/api")
public class CartResource {

	private final Logger log = LoggerFactory.getLogger(CartResource.class);

	private final CartService cartService;
	private final CartMapper cartMapper;

	public CartResource(final CartService cartService, final CartMapper cartMapper) {
		this.cartService = cartService;
		this.cartMapper = cartMapper;
	}

	/**
	 * GET /cart : Find a cart.
	 *
	 */
	@ApiOperation(value = "Cart", code = 200, notes = "Find a Cart.")
	@GetMapping("/cart/{cartId}")
	public ResponseEntity<Cart> findCart(@PathVariable Long cartId) {

		log.debug("Find a Cart");

		Cart cart = this.cartMapper.mapEntity(this.cartService.findCart(cartId));

		return ResponseEntity.ok().body(cart);
	}

	/**
	 * POST /cart : Create a cart.
	 *
	 */
	@ApiOperation(value = "Cart ", code = 201, notes = "Create a new Cart.")
	@PostMapping("/cart")
	public ResponseEntity<Cart> checkSso(@RequestBody Cart cart) {

		log.debug("Create a new Cart");

		Cart newCart = this.cartMapper.mapEntity(this.cartService.createCart(this.cartMapper.mapDb(cart)));

		return ResponseEntity.ok().body(newCart);
	}

	/**
	 * Delete /cart : Delete a cart.
	 *
	 */
	@ApiOperation(value = "Cart ", code = 200, notes = "Delete an existing cart.")
	@DeleteMapping("/cart")
	public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
		log.debug("Delete a cart");

		boolean result = this.cartService.deleteCart(cartId);
		
		if (result) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(400).build();
		}
	}

	/**
	 * Post /cart_product : Add a carts product.
	 *
	 */
	@ApiOperation(value = "Cart ", code = 201, notes = "Add a carts product.")
	@PostMapping("/cart_product")
	public ResponseEntity<Void> addCartProduct(@PathVariable Long cartId, @PathVariable Long productId) {
		log.debug("Add a carts product");

		boolean result = this.cartService.addProduct(cartId, productId);
		
		if (result) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(400).build();
		}
	}

	/**
	 * Delete /cart : Delete a carts product.
	 *
	 */
	@ApiOperation(value = "Cart ", code = 200, notes = "Delete a carts product.")
	@DeleteMapping("/cart_product")
	public ResponseEntity<Void> deleteCartProduct(@PathVariable Long cartId, @PathVariable Long productId) {
		log.debug("Delete a carts product");

		boolean result = this.cartService.removeProduct(cartId, productId);
		
		if (result) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(400).build();
		}
	}

}
