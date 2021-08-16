package com.onebox.shopping.domain.repository.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.onebox.shopping.domain.model.Cart;
import com.onebox.shopping.domain.model.Product;
import com.onebox.shopping.domain.repository.CartRepository;

@Component
@Scope("singleton")
public class CartRepositoryCache implements CartRepository {

	public List<Cart> carts;
	
	public Cart findCart(final Long cartId) {
		if (this.carts != null) {
			Optional<Cart> optCart = this.carts.stream().filter(ca -> ca.getId().equals(cartId)).findAny();
			return optCart.isPresent() ? optCart.get() : null;
		} else {
			this.carts = new ArrayList<>();
			return null;
		}
	}

	public Long createCart(final Cart cart) {
		if (this.carts == null) {
			this.carts = new ArrayList<>();
		}
		Cart currentCart = this.findCart(cart.getId());
		if (currentCart == null) {
			this.carts.add(cart);
		} else {
			this.carts.remove(currentCart);
			this.carts.add(cart);
		}
		return cart.getId();
	}

	public boolean deleteCart(final Long cartId) {
		Cart cart = this.findCart(cartId);
		if (cart != null) {
			this.carts.remove(cart);
			return true;
		}
		return false;
	}
	
	public boolean addProduct(final Long cartId, final Long productId) {
		try {
			Cart currentCart = this.findCart(cartId);
			if (currentCart != null) {
				Cart cart = currentCart;
				Product product = new Product(productId);
				if (cart.getProducts() == null) {
					cart.setProducts(new ArrayList<>());
				}
				cart.getProducts().add(product);
				this.deleteCart(cartId);
				this.createCart(cart);
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			return false;
		}
	}
	
	public boolean removeProduct(final Long cartId, final Long productId) {
		try {
			Cart currentCart = this.findCart(cartId);
			if (currentCart != null) {
				Cart cart = currentCart;
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
			} else {
				return false;
			}
		} catch(Exception e) {
			return false;
		}
	}

}
