package com.onebox.shopping.domain.repository.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.onebox.shopping.domain.repository.CartRepository;
import com.onebox.shopping.rest.model.Cart;
import com.onebox.shopping.rest.model.Product;

@Component
public class CartRepositoryCache implements CartRepository {

	public Cart findCart(final Long cartId) {
		Optional<Cart> optCart = cachedList().stream().filter(ca -> ca.getId().equals(cartId)).findAny();
		return optCart.isPresent() ? optCart.get() : null;
	}

	@CachePut(value = "carts", key = "#cartDb.id")
	public Long createCart(final Cart cart) {
		return Long.getLong("1");
	}

	@CacheEvict(value = "carts", key = "#cartId")
	public boolean deleteCart(final Long cartId) {
		return true;
	}
	
	public boolean addProduct(final Long cartId, final Long productId) {
		try {
			Cart cart = this.findCart(cartId);
			Product product = new Product(productId);
			if (cart.getProducts() == null) {
				cart.setProducts(new ArrayList<>());
			}
			cart.getProducts().add(product);
			this.createCart(cart);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public boolean removeProduct(final Long cartId, final Long productId) {
		try {
			Cart cart = this.findCart(cartId);
			if (cart.getProducts() == null) {
				cart.setProducts(new ArrayList<>());
			} else {
				Optional<Product> optProduct = cart.getProducts().stream().filter(pr -> pr.getId().equals(productId)).findAny();
				if (optProduct.isPresent()) {
					cart.getProducts().remove(optProduct.get());
					this.createCart(cart);
				}
			}
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Cacheable("carts")
	private List<Cart> cachedList() {
		return new ArrayList<>();
	}

}
