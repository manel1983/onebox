package com.onebox.shopping.domain.model;

import java.io.Serializable;
import java.util.List;

//@Entity
//@Table(name = "Cart")
public class CartDb implements Serializable {

	private static final long serialVersionUID = 1L;

	public CartDb() {
	}

	public CartDb(Long id) {
		setId(id);
	}

//	@Id
//	@Column(name = "Id_c", length = 32, nullable = false)
	private Long id;

//	@Column(name = "username")
	private String username;

	private List<ProductDb> products;

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

	public List<ProductDb> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDb> products) {
		this.products = products;
	}

}
