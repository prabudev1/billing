package com.gpware.billing.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

public class BillingDTO implements Serializable {
	private static final long serialVersionUID = 2530083827640916387L;
	private int id;
	private Integer billNum;
	private CustomerDTO customer;
	private BigDecimal totItems;
	private BigDecimal totValue;
	private BigDecimal gstStatePercent;
	private BigDecimal gstCentralPercent;
	private BigDecimal gstStateVal;
	private BigDecimal gstCentralVal;
	private BigDecimal discount;
	private BigDecimal total;
	private String sts;
	private String notes;
	private Timestamp billingDate;
	private String createdBy;
	private Timestamp createdOn;

	private Set<BillingDetailsDTO> billingItems;

	public BillingDTO() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
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

	public Set<BillingDetailsDTO> getBillingItems() {
		return billingItems;
	}

	public void setBillingItems(Set<BillingDetailsDTO> billingItems) {
		this.billingItems = billingItems;
	}

	public Integer getBillNum() {
		return billNum;
	}

	public void setBillNum(Integer billNum) {
		this.billNum = billNum;
	}

	public Timestamp getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Timestamp billingDate) {
		this.billingDate = billingDate;
	}

}