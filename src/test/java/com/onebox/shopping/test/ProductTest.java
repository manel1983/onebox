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
public class ProductTest {

	private static final Logger LOG = LoggerFactory.getLogger(ProductTest.class);

	@Test
	@DisplayName("Find a Product")
	public void findProduct() {
		try {
			Assertions.assertTrue(true);
		} catch(Exception e) {
			LOG.error("Error on Find Product", e);
			Assertions.assertTrue(false);
		}
	}

	@Test
	@DisplayName("Search a Product")
	public void searchProduct() {
		try {
			Assertions.assertTrue(true);
		} catch(Exception e) {
			LOG.error("Error on Search a Product", e);
			Assertions.assertTrue(false);
		}
	}

}
