package com.onebox.shopping.rest.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * A Cart Poduct Dto.
 */
public class CartProductDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public CartProductDto() {
	}

	@NotNull
	@ApiModelProperty(value = "The cart id", example = "23")
	private Long cartId;

	@NotNull
	@ApiModelProperty(value = "The Product id", example = "1", required = true)
	private Long productId;

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

}
