package com.onebox.shopping.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

//@Entity
//@Table(name = "Product")
public class ProductDb implements Serializable {

	private static final long serialVersionUID = 1L;

	public ProductDb() {
	}

	public ProductDb(Long id) {
		setId(id);
	}

//	@Id
//	@Column(name = "Id_c", length = 32, nullable = false)
	private Long id;

//	@Column(name = "description")
	private String descripton;

//	@Column(name = "amount")
	private BigDecimal amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripton() {
		return descripton;
	}

	public void setDescripton(String descripton) {
		this.descripton = descripton;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
