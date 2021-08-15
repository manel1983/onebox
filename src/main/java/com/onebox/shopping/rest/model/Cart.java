package com.onebox.shopping.rest.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * A Cart.
 */
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

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
	@ApiModelProperty(value = "The products")
	private List<Product> products;

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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
