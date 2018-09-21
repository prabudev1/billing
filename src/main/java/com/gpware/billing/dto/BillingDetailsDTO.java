package com.gpware.billing.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class BillingDetailsDTO implements Serializable {

	private static final long serialVersionUID = 4518171636618554083L;

	private ProductDTO product;
	private BillingDTO billing;
	private BigDecimal qty;
	private BigDecimal discountPercent;
	private BigDecimal value;
	private String sts;
	private String notes;
	private String createdBy;

	public BillingDetailsDTO() {

	}

	public BillingDetailsDTO(int id, BillingDTO billing, BigDecimal qty, BigDecimal discountPercent, BigDecimal value, String sts, String notes, String createdBy, Timestamp createdOn) {
		super();
		this.billing = billing;
		this.qty = qty;
		this.discountPercent = discountPercent;
		this.value = value;
		this.sts = sts;
		this.notes = notes;
		this.createdBy = createdBy;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public BillingDTO getBilling() {
		return billing;
	}

	public void setBilling(BillingDTO billing) {
		this.billing = billing;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(BigDecimal discountPercent) {
		this.discountPercent = discountPercent;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}