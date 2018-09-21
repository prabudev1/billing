package com.gpware.billing.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BILLING")
public class Billing implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;

	@OneToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	@Column(name = "TOT_ITEMS")
	private BigDecimal totItems;

	@Column(name = "TOT_VALUE")
	private BigDecimal totValue;

	@Column(name = "SGST_PERCENT")
	private BigDecimal gstStatePercent;

	@Column(name = "CGST_PERCENT")
	private BigDecimal gstCentralPercent;

	@Column(name = "SGST_VALUE")
	private BigDecimal gstStateVal;

	@Column(name = "CGST_VALUE")
	private BigDecimal gstCentralVal;

	@Column(name = "DISCOUNT")
	private BigDecimal discount;

	@Column(name = "GRAND_TOTAL")
	private BigDecimal total;

	@Column(name = "STATUS")
	private String sts;

	@Column(name = "NOTES")
	private String notes;

	@Column(name = "CREATEDBY")
	private String createdBy;

	@Column(name = "CREATEDON", insertable= false, updatable = false)
	private Timestamp createdOn;

	@OneToMany(mappedBy = "billing", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<BillingDetails> billingItems;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public BigDecimal getTotItems() {
		return totItems;
	}

	public void setTotItems(BigDecimal totItems) {
		this.totItems = totItems;
	}

	public BigDecimal getTotValue() {
		return totValue;
	}

	public void setTotValue(BigDecimal totValue) {
		this.totValue = totValue;
	}

	public BigDecimal getGstStatePercent() {
		return gstStatePercent;
	}

	public void setGstStatePercent(BigDecimal gstStatePercent) {
		this.gstStatePercent = gstStatePercent;
	}

	public BigDecimal getGstCentralPercent() {
		return gstCentralPercent;
	}

	public void setGstCentralPercent(BigDecimal gstCentralPercent) {
		this.gstCentralPercent = gstCentralPercent;
	}

	public BigDecimal getGstStateVal() {
		return gstStateVal;
	}

	public void setGstStateVal(BigDecimal gstStateVal) {
		this.gstStateVal = gstStateVal;
	}

	public BigDecimal getGstCentralVal() {
		return gstCentralVal;
	}

	public void setGstCentralVal(BigDecimal gstCentralVal) {
		this.gstCentralVal = gstCentralVal;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
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

	public Set<BillingDetails> getBillingItems() {
		return billingItems;
	}

	public void setBillingItems(Set<BillingDetails> billingItems) {
		this.billingItems = billingItems;
	}

}