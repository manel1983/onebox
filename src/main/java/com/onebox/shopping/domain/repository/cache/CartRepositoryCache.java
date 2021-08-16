package com.onebox.shopping.domain.repository.cache;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.onebox.shopping.domain.model.Cart;
import com.onebox.shopping.domain.model.CartProducts;
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
			cart.setCreationTime(LocalDateTime.now());
			this.carts.add(cart);
		} else {
			cart.setCreationTime(currentCart.getCreationTime());
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

	public boolean addProduct(final Long cartId, final Long productId, final Long amount) {
		try {
			Cart currentCart = this.findCart(cartId);
			if (currentCart != null) {
				Cart cart = currentCart;
				
				Optional<CartProducts> optCartProducts = cart.getCartProducts().stream().filter(pr -> pr.getProductId().equals(productId)).findAny();
				if (optCartProducts.isPresent()) {
					CartProducts currentCartProducts = optCartProducts.get();
					cart.getCartProducts().remove(currentCartProducts);
					currentCartProducts.setAmount(currentCartProducts.getAmount() + amount);
					cart.getCartProducts().add(currentCartProducts);
				} else {
					CartProducts cartProducts = new CartProducts();
					cartProducts.setCartId(cartId);
					cartProducts.setProductId(productId);
					cartProducts.setAmount(amount);

					cart.getCartProducts().add(cartProducts);
				}
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
				if (cart.getCartProducts() == null) {
					cart.setCartProducts(new ArrayList<>());
				} else {
					Optional<CartProducts> optCartProduct = cart.getCartProducts().stream().filter(pr -> pr.getProductId().equals(productId)).findAny();
					if (optCartProduct.isPresent()) {
						cart.getCartProducts().remove(optCartProduct.get());
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

	public boolean isCartExpired(final Long cartId) {
		Cart cart = this.findCart(cartId);
		if (cart == null) {
			return true;
		}
		long minutes = cart.getCreationTime().until(LocalDateTime.now(), ChronoUnit.MINUTES);
		if (minutes > 9) {
			this.deleteCart(cart.getId());
			return true;
		}
		return false;
	}
}
