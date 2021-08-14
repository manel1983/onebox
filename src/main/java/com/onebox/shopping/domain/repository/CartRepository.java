package com.onebox.shopping.domain.repository;

import org.springframework.stereotype.Repository;

import com.onebox.shopping.domain.model.CartDb;

@Repository
public interface CartRepository {

	CartDb findCart(final Long cartId);

	Long createCart(final CartDb cartDb);
	
	boolean deleteCart(final Long cartId);
	
	boolean addProduct(final Long cartId, final Long productId);
	
	boolean removeProduct(final Long cartId, final Long productId);

}
