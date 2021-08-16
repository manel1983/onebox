package com.onebox.shopping.service.impl;

import org.springframework.stereotype.Service;

import com.onebox.shopping.domain.model.Cart;
import com.onebox.shopping.domain.repository.CartRepository;
import com.onebox.shopping.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	private CartRepository cartRepository;

	public CartServiceImpl(final CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	public Cart findCart(final Long cartId) {
		return this.cartRepository.findCart(cartId);
	}

	public Cart createCart(final Cart cart) {
		Long cartId = this.cartRepository.createCart(cart);
		return new Cart(cartId);
	}

	public boolean deleteCart(final Long cartId) {
		return this.cartRepository.deleteCart(cartId);
	}

	public boolean addProduct(final Long cartId, final Long productId, final Long amount) {
		return this.cartRepository.addProduct(cartId, productId, amount);
	}
	
	public boolean removeProduct(final Long cartId, final Long productId) {
		return this.cartRepository.removeProduct(cartId, productId);
	}

	public boolean isCartExpired(final Long cartId) {
		return this.cartRepository.isCartExpired(cartId);
	}
}
