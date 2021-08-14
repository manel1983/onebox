package com.onebox.shopping.service;

import com.onebox.shopping.domain.model.CartDb;

public interface CartService {

	CartDb findCart(final Long cartId);

	CartDb createCart(final CartDb cartDb);

	boolean deleteCart(final Long cartId);

	boolean addProduct(final Long cartId, final Long productId);
	
	boolean removeProduct(final Long cartId, final Long productId);

}
