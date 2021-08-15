package com.onebox.shopping.service;

import com.onebox.shopping.rest.model.Cart;

public interface CartService {

	Cart findCart(final Long cartId);

	Cart createCart(final Cart cart);

	boolean deleteCart(final Long cartId);

	boolean addProduct(final Long cartId, final Long productId);
	
	boolean removeProduct(final Long cartId, final Long productId);

}
