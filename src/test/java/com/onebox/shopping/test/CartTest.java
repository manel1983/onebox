package com.onebox.shopping.test;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.onebox.shopping.domain.model.Cart;
import com.onebox.shopping.domain.repository.CartRepository;
import com.onebox.shopping.service.CartService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class CartTest {

	private static final Logger LOG = LoggerFactory.getLogger(CartTest.class);

	@MockBean
	private CartRepository cartRepository;

	@Autowired
	private CartService cartService;

	final static Long CART_ID = Long.valueOf("30");
	final static Long PRODUCT_ID1 = Long.valueOf("1");

	@Test
	@DisplayName("Check Create a Cart")
	public void checkCreateCart() {
		try {
			Cart cart = new Cart();
			cart.setId(Long.valueOf(CART_ID));
			cart.setCartProducts(new ArrayList<>());
			cart.setUsername("MSA");

			Mockito.when(cartRepository.createCart(cart)).thenReturn(CART_ID);
			Cart createdCart = this.cartService.createCart(cart);
			Assertions.assertNotNull(createdCart);
			Assertions.assertEquals(createdCart.getId(), cart.getId());
		} catch(Exception e) {
			LOG.error("Error on Cart creation", e);
			Assertions.assertTrue(false);
		}
	}

	@Test
	@DisplayName("Find a Cart")
	public void findCart() {
		try {
			Mockito.when(cartRepository.findCart(CART_ID)).thenReturn(new Cart(CART_ID));
			Cart existingCart = this.cartService.findCart(CART_ID);
			Assertions.assertNotNull(existingCart);
			Assertions.assertEquals(existingCart.getId(), CART_ID);
			Assertions.assertTrue(existingCart.getCartProducts() == null || existingCart.getCartProducts().isEmpty());
		} catch(Exception e) {
			LOG.error("Error on Find a Cart", e);
			Assertions.assertTrue(false);
		}
	}

	@Test
	@DisplayName("Add a Carts Product")
	public void addCartProduct() {
		try {
			Mockito.when(cartRepository.addProduct(CART_ID, PRODUCT_ID1, Long.valueOf(1))).thenReturn(true);
			boolean result = this.cartService.addProduct(CART_ID, PRODUCT_ID1, Long.valueOf(1));
			Assertions.assertTrue(result);
		} catch(Exception e) {
			LOG.error("Error on add a Carts Product", e);
			Assertions.assertTrue(false);
		}
	}

	@Test
	@DisplayName("Remove a Carts Product")
	public void removeCartProduct() {
		try {
			Mockito.when(cartRepository.removeProduct(CART_ID, PRODUCT_ID1)).thenReturn(true);
			boolean result = this.cartService.removeProduct(CART_ID, PRODUCT_ID1);
			Assertions.assertTrue(result);
		} catch(Exception e) {
			LOG.error("Error on remove a Carts Product", e);
			Assertions.assertTrue(false);
		}
	}

	@Test
	@DisplayName("Delete a Cart")
	public void deleteCart() {
		try {
			Mockito.when(cartRepository.deleteCart(CART_ID)).thenReturn(true);
			boolean result = this.cartService.deleteCart(CART_ID);
			Assertions.assertTrue(result);
		} catch(Exception e) {
			LOG.error("Error on removing a Cart", e);
			Assertions.assertTrue(false);
		}
	}

}
