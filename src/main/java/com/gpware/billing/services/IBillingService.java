package com.gpware.billing.services;

import java.io.IOException;
import java.util.List;

import com.gpware.billing.dto.BillingReportDTO;
import com.gpware.billing.model.Billing;

public interface IBillingService {
	
	List<Billing> getBillingList(String userIdentifier, String fromDate, String toDate, String orderBY);

	Billing getBillingById(int billingId);

	void addBilling(Billing billing);

	void deleteBilling(int billingId);
	
	BillingReportDTO getReportData(String userIdentifier, Integer reportDays);

	byte[] downloadExcel(String userIdentifier, String fromDate, String toDate, String orderBY) throws IOException;

}
