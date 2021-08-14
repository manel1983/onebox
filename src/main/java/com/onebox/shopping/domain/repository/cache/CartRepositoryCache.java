package com.onebox.shopping.domain.repository.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.onebox.shopping.domain.model.CartDb;
import com.onebox.shopping.domain.repository.CartRepository;

@Component
public class CartRepositoryCache implements CartRepository {

	public CartDb findCart(final Long cartId) {
		Optional<CartDb> optCart = cachedList().stream().filter(ca -> ca.getId().equals(cartId)).findAny();
		return optCart.isPresent() ? optCart.get() : null;
	}

	public Long createCart(final CartDb cartDb) {
		cachedList().add(cartDb);
	}
	
	public boolean deleteCart(final Long cartId) {
		
	}
	
	public boolean addProduct(final Long cartId, final Long productId) {
		
	}
	
	public boolean removeProduct(final Long cartId, final Long productId) {
		
	}

	@Cacheable("carts")
	private List<CartDb> cachedList() {
		return new ArrayList<>();
	}

}
