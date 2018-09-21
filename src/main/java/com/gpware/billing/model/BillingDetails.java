package com.gpware.billing.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BILLING_DET")
public class BillingDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BILLING_ID", referencedColumnName = "ID")
	private Billing billing;

	@OneToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product prduct;

	@Column(name = "QTY")
	private BigDecimal qty;

	@Column(name = "DISCOUNT_PERCENT")
	private BigDecimal discountPercent;

	@Column(name = "VALUE")
	private BigDecimal value;

	@Column(name = "STATUS")
	private String sts;

	@Column(name = "NOTES")
	private String notes;

	@Column(name = "CREATEDBY")
	private String createdBy;

	@Column(name = "CREATEDON", insertable= false, updatable = false)
	private Timestamp createdOn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Billing getBilling() {
		return billing;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	public Product getPrduct() {
		return prduct;
	}

	public void setPrduct(Product prduct) {
		this.prduct = prduct;
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

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

}