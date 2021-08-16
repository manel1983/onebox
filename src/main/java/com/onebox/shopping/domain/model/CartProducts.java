package com.onebox.shopping.domain.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * A Cart Products.
 */
public class CartProducts implements Serializable {

	private static final long serialVersionUID = 1L;

	public CartProducts() {
	}

	@NotNull
	@ApiModelProperty(value = "The cart id", example = "23")
	private Long cartId;

	@NotNull
	@ApiModelProperty(value = "The product id", example = "1", required = true)
	private Long productId;

	@NotNull
	@ApiModelProperty(value = "The amount")
	private Long amount;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

}
