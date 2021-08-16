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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onebox.shopping.domain.model.Cart;
import com.onebox.shopping.rest.model.CartProductDto;
import com.onebox.shopping.service.CartService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;

/**
 * REST controller for managing Carts.
 */

@RestController
@Api(value = "/api/cart")
@SwaggerDefinition(tags = {
	@io.swagger.annotations.Tag(name = "Carts Resource", description = "Carts functionalities")
})
@RequestMapping("/api")
public class CartResource {

	private final Logger log = LoggerFactory.getLogger(CartResource.class);

	private final CartService cartService;

	public CartResource(final CartService cartService) {
		this.cartService = cartService;
	}

	/**
	 * GET /cart : Find a cart.
	 *
	 */
	@ApiOperation(value = "Cart", code = 200, notes = "Find a Cart.", consumes = "application/json", produces = "application/json")
	@GetMapping("/cart/{cartId}")
	public ResponseEntity findCart(@PathVariable Long cartId) {
		log.debug("Find a Cart");

		if (this.cartService.isCartExpired(cartId)) {
			return ResponseEntity.status(400).body("The cart has expired.");
		}

		Cart cart = this.cartService.findCart(cartId);

		return ResponseEntity.ok().body(cart);
	}

	/**
	 * POST /cart : Create a cart.
	 *
	 */
	@ApiOperation(value = "Cart ", code = 201, notes = "Create a new Cart.", consumes = "application/json", produces = "application/json")
	@PostMapping("/cart")
	public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
		log.debug("Create a new Cart");

		Cart newCart = this.cartService.createCart(cart);

		return ResponseEntity.ok().body(newCart);
	}

	/**
	 * Delete /cart : Delete a cart.
	 *
	 */
	@ApiOperation(value = "Cart ", code = 200, notes = "Delete an existing cart.", consumes = "application/json", produces = "application/json")
	@DeleteMapping("/cart/{cartId}")
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
	@ApiOperation(value = "Cart ", code = 201, notes = "Add a carts product.", consumes = "application/json", produces = "application/json")
	@PostMapping("/cart_product")
	public ResponseEntity addCartProduct(@RequestBody CartProductDto cartProductDto) {
		log.debug("Add a carts product");

		if (this.cartService.isCartExpired(cartProductDto.getCartId())) {
			return ResponseEntity.status(400).body("The cart has expired.");
		}

		boolean result = this.cartService.addProduct(cartProductDto.getCartId(), cartProductDto.getProductId());
		
		if (result) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(400).body("An error occurred adding the product");
		}
	}

	/**
	 * Delete /cart : Delete a carts product.
	 *
	 */
	@ApiOperation(value = "Cart ", code = 200, notes = "Delete a carts product.", consumes = "application/json", produces = "application/json")
	@DeleteMapping("/cart_product")
	public ResponseEntity deleteCartProduct(@RequestParam(name = "cartId") Long cartId, @RequestParam(name = "productId") Long productId) {
		log.debug("Delete a carts product");

		if (this.cartService.isCartExpired(cartId)) {
			return ResponseEntity.status(400).body("The cart has expired.");
		}

		boolean result = this.cartService.removeProduct(cartId, productId);
		
		if (result) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(400).body("An error ocurred trying to remove the product");
		}
	}

}
