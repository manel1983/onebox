package com.onebox.shopping.test;

import java.util.ArrayList;
import java.util.List;

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

import com.onebox.shopping.domain.model.Product;
import com.onebox.shopping.domain.repository.ProductRepository;
import com.onebox.shopping.service.ProductService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class ProductTest {

	private static final Logger LOG = LoggerFactory.getLogger(ProductTest.class);

	@MockBean
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	final static Long PRODUCT_ID = Long.valueOf("1");
	
	@Test
	@DisplayName("Find a Product")
	public void findProduct() {
		try {
			Mockito.when(productRepository.findProduct(PRODUCT_ID)).thenReturn(new Product(PRODUCT_ID));
			Product product = this.productService.findProduct(PRODUCT_ID);
			Assertions.assertNotNull(product);
			Assertions.assertEquals(product.getId(), PRODUCT_ID);
		} catch(Exception e) {
			LOG.error("Error on Find Product", e);
			Assertions.assertTrue(false);
		}
	}

	@Test
	@DisplayName("Search a Product")
	public void searchProduct() {
		try {
			List<Product> products = new ArrayList<>();
			products.add(new Product(Long.valueOf("1")));
			products.add(new Product(Long.valueOf("2")));
			products.add(new Product(Long.valueOf("3")));

			Mockito.when(productRepository.searchProducts("ticket")).thenReturn(products);
			List<Product> founfProducts = this.productService.searchProducts("ticket");
			Assertions.assertNotNull(founfProducts);
			Assertions.assertEquals(products, founfProducts);
		} catch(Exception e) {
			LOG.error("Error on Search a Product", e);
			Assertions.assertTrue(false);
		}
	}

}
