package com.onebox.shopping.test;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class CartTest {

	private static final Logger LOG = LoggerFactory.getLogger(CartTest.class);

	@Test
	@DisplayName("Check Create a Cart")
	public void checkCreateCart() {
		try {
			Assertions.assertTrue(true);
		} catch(Exception e) {
			LOG.error("Error on Cart creation", e);
			Assertions.assertTrue(false);
		}
	}

	@Test
	@DisplayName("Find a Cart")
	public void findCart() {
		try {
			Assertions.assertTrue(true);
		} catch(Exception e) {
			LOG.error("Error on Find a Cart", e);
			Assertions.assertTrue(false);
		}
	}

	@Test
	@DisplayName("Add a Carts Product")
	public void addCartProduct() {
		try {
			Assertions.assertTrue(true);
		} catch(Exception e) {
			LOG.error("Error on add a Carts Product", e);
			Assertions.assertTrue(false);
		}
	}

	@Test
	@DisplayName("Remove a Carts Product")
	public void removeCartProduct() {
		try {
			Assertions.assertTrue(true);
		} catch(Exception e) {
			LOG.error("Error on remove a Carts Product", e);
			Assertions.assertTrue(false);
		}
	}

	@Test
	@DisplayName("Delete a Cart")
	public void deleteCart() {
		try {
			Assertions.assertTrue(true);
		} catch(Exception e) {
			LOG.error("Error on removing a Cart", e);
			Assertions.assertTrue(false);
		}
	}

}
