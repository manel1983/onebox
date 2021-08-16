package com.onebox.shopping.domain.repository.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.onebox.shopping.domain.model.Cart;
import com.onebox.shopping.domain.model.Product;
import com.onebox.shopping.domain.repository.CartRepository;

@Component
@Scope("singleton")
public class CartRepositoryCache implements CartRepository {

	public List<Cart> cachedCarts;
	
	public Cart findCart(final Long cartId) {
		if (this.cachedCarts != null) {
			Optional<Cart> optCart = this.cachedCarts.stream().filter(ca -> ca.getId().equals(cartId)).findAny();
			return optCart.isPresent() ? optCart.get() : null;
		} else {
			this.cachedCarts = new ArrayList<>();
			return null;
		}
	}

	public Long createCart(final Cart cart) {
		if (this.cachedCarts == null) {
			this.cachedCarts = new ArrayList<>();
		}
		this.cachedCarts.add(cart);
		return cart.getId();
	}

	public boolean deleteCart(final Long cartId) {
		Cart cart = this.findCart(cartId);
		if (cart != null) {
			this.cachedCarts.remove(cart);
			return true;
		}
		return false;
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
		if (cachedCarts == null) {
			cachedCarts = new ArrayList<>();
		}
		return cachedCarts;
	}

}
