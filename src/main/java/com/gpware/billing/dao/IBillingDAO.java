package com.gpware.billing.dao;

import java.util.List;

import com.gpware.billing.dto.BillingReportDTO;
import com.gpware.billing.model.Billing;

public interface IBillingDAO {

	Billing getBillingById(int billingId);

	List<Billing> getAllBillings(Integer limitRowCount);
	
	void addBilling(Billing billing);

	void deleteBilling(int billingId);
	
	BillingReportDTO getBillReportCOunt();

	List<Billing> getBillingList(String fromDate, String toDate, String orderBy);

}
