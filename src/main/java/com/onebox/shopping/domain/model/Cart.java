package com.onebox.shopping.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * A Cart.
 */
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	public Cart() {
	}

	public Cart(Long id) {
		setId(id);
	}

	@NotNull
	@ApiModelProperty(value = "The cart id", example = "23")
	private Long id;

	@NotNull
	@ApiModelProperty(value = "The username", example = "test", required = true)
	private String username;

	@NotNull
	@ApiModelProperty(value = "The cart products")
	private List<CartProducts> cartProducts;

	@ApiModelProperty(value = "The creation time")
	private LocalDateTime creationTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<CartProducts> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<CartProducts> cartProducts) {
		this.cartProducts = cartProducts;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

}
