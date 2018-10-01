package com.gpware.billing.dao;

import java.util.List;

import com.gpware.billing.dto.BillingReportDTO;
import com.gpware.billing.model.Billing;

public interface IBillingDAO {

	Billing getBillingById(int billingId);

	List<Billing> getAllBillings(String userIdentifier, Integer limitRowCount);
	
	void addBilling(Billing billing);

	void deleteBilling(int billingId);
	
	BillingReportDTO getBillReportCOunt(String userIdentifier);

	List<Billing> getBillingList(String userIdentifier, String fromDate, String toDate, String orderBy);

}
