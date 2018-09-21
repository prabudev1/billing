package com.gpware.billing.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class BillingReportDTO implements Serializable {
	private static final long serialVersionUID = 2530083827640916387L;
	private List<String> lineChartLabels;
	private List<Integer> lineChartValues;
	private BigDecimal totRevenue;
	private BigDecimal totCustomer;
	private BigDecimal totInvoices;
	private BigDecimal totProducts;

	private List<BillingDTO> billingItems;

	public BillingReportDTO() {

	}

	public List<String> getLineChartLabels() {
		return lineChartLabels;
	}

	public void setLineChartLabels(List<String> lineChartLabels) {
		this.lineChartLabels = lineChartLabels;
	}

	public List<Integer> getLineChartValues() {
		return lineChartValues;
	}

	public void setLineChartValues(List<Integer> lineChartValues) {
		this.lineChartValues = lineChartValues;
	}

	public BigDecimal getTotRevenue() {
		return totRevenue;
	}

	public void setTotRevenue(BigDecimal totRevenue) {
		this.totRevenue = totRevenue;
	}

	public BigDecimal getTotCustomer() {
		return totCustomer;
	}

	public void setTotCustomer(BigDecimal totCustomer) {
		this.totCustomer = totCustomer;
	}

	public BigDecimal getTotInvoices() {
		return totInvoices;
	}

	public void setTotInvoices(BigDecimal totInvoices) {
		this.totInvoices = totInvoices;
	}

	public BigDecimal getTotProducts() {
		return totProducts;
	}

	public void setTotProducts(BigDecimal totProducts) {
		this.totProducts = totProducts;
	}

	public List<BillingDTO> getBillingItems() {
		return billingItems;
	}

	public void setBillingItems(List<BillingDTO> billingItems) {
		this.billingItems = billingItems;
	}

}