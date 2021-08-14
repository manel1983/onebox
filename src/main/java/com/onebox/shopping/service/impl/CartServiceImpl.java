package com.onebox.shopping.service.impl;

import org.springframework.stereotype.Service;

import com.onebox.shopping.domain.model.CartDb;
import com.onebox.shopping.domain.repository.CartRepository;
import com.onebox.shopping.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	private CartRepository cartRepository;
	
	public CartServiceImpl(final CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	public CartDb findCart(final Long cartId) {
		return this.cartRepository.findCart(cartId);
	}

	public CartDb createCart(final CartDb cartDb) {
		Long cartId = this.cartRepository.createCart(cartDb);
		return new CartDb(cartId);
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

}
