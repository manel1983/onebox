package com.onebox.shopping.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * A Cart.
 */
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	public Product() {
	
	}
	
	public Product(Long id) {
		setId(id);
	}

	@NotNull
	@ApiModelProperty(value = "The product id", example = "23")
	private Long id;

	@NotNull
	@ApiModelProperty(value = "The product description", example = "product 1")
	private String description;

	@NotNull
	@ApiModelProperty(value = "The product price", example = "12.50")
	private BigDecimal price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
