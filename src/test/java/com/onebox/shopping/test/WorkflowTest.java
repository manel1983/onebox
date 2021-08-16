package com.onebox.shopping.test;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.onebox.shopping.domain.model.Cart;
import com.onebox.shopping.service.CartService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class WorkflowTest {

	@Autowired
	private CartService cartService;

	final static Long CART_ID = Long.valueOf("30");
	final static Long PRODUCT_ID1 = Long.valueOf("1");

	@Test
	@DisplayName("Complete not mocked test")
	public void completeTest() {
		try {
			// Create a new cart
			Cart cart = new Cart();
			cart.setId(Long.valueOf(CART_ID));
			cart.setProducts(new ArrayList<>());
			cart.setUsername("MSA");

			Cart createdCart = this.cartService.createCart(cart);
			Assertions.assertNotNull(createdCart);
			Assertions.assertEquals(createdCart.getId(), cart.getId());
			
			// Find created cart
			Cart existingCart = this.cartService.findCart(CART_ID);
			Assertions.assertNotNull(existingCart);
			Assertions.assertEquals(existingCart.getId(), CART_ID);
			Assertions.assertTrue(existingCart.getProducts() == null || existingCart.getProducts().isEmpty());
			

			// Add product
			boolean result = this.cartService.addProduct(CART_ID, PRODUCT_ID1);
			Assertions.assertTrue(result);
			
			// Find cart with product
			Cart existingCartWithProduct = this.cartService.findCart(CART_ID);
			Assertions.assertNotNull(existingCartWithProduct);
			Assertions.assertEquals(existingCartWithProduct.getId(), CART_ID);
			Assertions.assertTrue(existingCartWithProduct.getProducts() != null);
			Assertions.assertEquals(existingCartWithProduct.getProducts().get(0).getId(), PRODUCT_ID1);
			
		
			// Remove product
			boolean removedProductResult = this.cartService.removeProduct(CART_ID, PRODUCT_ID1);
			Assertions.assertTrue(removedProductResult);
			
			// Delete cart
			boolean deleteCartResult = this.cartService.deleteCart(CART_ID);
			Assertions.assertTrue(deleteCartResult);

			// find deleted Cart
			Cart deletedCart = this.cartService.findCart(CART_ID);
			Assertions.assertNull(deletedCart);

		} catch(Exception e) {
			Assertions.assertTrue(false);
		}
	}
}
