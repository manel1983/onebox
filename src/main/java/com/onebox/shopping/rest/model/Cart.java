package com.onebox.shopping.rest.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * A Cart.
 */
@lombok.Data
@lombok.NoArgsConstructor
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@ApiModelProperty(value = "The cart id", example = "23")
	private Long id;

	@NotNull
	@ApiModelProperty(value = "The username", example = "test", required = true)
	private String username;

	@NotNull
	@ApiModelProperty(value = "The products")
	private List<Product> products;
	
}
