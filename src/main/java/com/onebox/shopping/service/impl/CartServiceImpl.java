package com.onebox.shopping.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.onebox.shopping.domain.model.Cart;
import com.onebox.shopping.domain.model.Product;
import com.onebox.shopping.domain.repository.CartRepository;
import com.onebox.shopping.domain.repository.ProductRepository;
import com.onebox.shopping.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	private CartRepository cartRepository;
	private ProductRepository productRepository;
	
	public CartServiceImpl(final CartRepository cartRepository, final ProductRepository productRepository) {
		this.cartRepository = cartRepository;
		this.productRepository = productRepository;
	}

	public Cart findCart(final Long cartId) {
		Cart cart = this.cartRepository.findCart(cartId);
		if (cart != null && cart.getProducts() != null && !cart.getProducts().isEmpty()) {
			List<Product> fullProducts = new ArrayList<>();
			for(Product product : cart.getProducts()) {
				fullProducts.add(this.productRepository.findProduct(product.getId()));
			}
			cart.setProducts(fullProducts);
		}
		return cart;
	}

	public Cart createCart(final Cart cart) {
		Long cartId = this.cartRepository.createCart(cart);
		return new Cart(cartId);
	}

	public boolean deleteCart(final Long cartId) {
		return this.cartRepository.deleteCart(cartId);
	}

	public boolean addProduct(final Long cartId, final Long productId) {
		return this.cartRepository.addProduct(cartId, productId);
	}
	
	public boolean removeProduct(final Long cartId, final Long productId) {
		return this.cartRepository.removeProduct(cartId, productId);
	}

	public boolean isCartExpired(final Long cartId) {
		return this.cartRepository.isCartExpired(cartId);
	}
}
