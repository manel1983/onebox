package com.onebox.shopping.domain.repository;

import org.springframework.stereotype.Repository;

import com.onebox.shopping.domain.model.Cart;

@Repository
public interface CartRepository {

	Cart findCart(final Long cartId);

	Long createCart(final Cart cart);
	
	boolean deleteCart(final Long cartId);
	
	boolean addProduct(final Long cartId, final Long productId);
	
	boolean removeProduct(final Long cartId, final Long productId);

}
