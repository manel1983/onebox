package com.onebox.shopping.rest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * A Cart.
 */
@lombok.Data
@lombok.NoArgsConstructor
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@ApiModelProperty(value = "The product id", example = "23")
	private Long id;

	@NotNull
	@ApiModelProperty(value = "The product description", example = "product 1")
	private String description;

	@NotNull
	@ApiModelProperty(value = "The product price", example = "12.50")
	private BigDecimal amount;

}
