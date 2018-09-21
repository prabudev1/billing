package com.gpware.billing.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.gpware.billing.dto.BillingDTO;
import com.gpware.billing.dto.BillingDetailsDTO;
import com.gpware.billing.dto.CustomerDTO;
import com.gpware.billing.dto.ProductDTO;
import com.gpware.billing.model.Billing;
import com.gpware.billing.model.BillingDetails;
import com.gpware.billing.model.Customer;
import com.gpware.billing.model.Product;

public class BillingHelper implements Serializable {

	private static final long serialVersionUID = -2831280666610938753L;

	public BillingDTO copyBilling(Billing billing) {
		/*Copy high level properties*/
		BillingDTO dtoBilling = new BillingDTO();
		BeanUtils.copyProperties(billing, dtoBilling);
		
		/*Copy billing detail list properties*/
		Set<BillingDetailsDTO> dtoBillingDtList = new HashSet<BillingDetailsDTO>();
		for (BillingDetails billingDt : billing.getBillingItems()) {
			BillingDetailsDTO dtoBillingDet = new BillingDetailsDTO();
			BeanUtils.copyProperties(billingDt, dtoBillingDet);
			
			if (billingDt.getPrduct() != null) {
				ProductDTO dtoProduct = new ProductDTO();
				BeanUtils.copyProperties(billingDt.getPrduct(), dtoProduct);
				dtoBillingDet.setProduct(dtoProduct);
			}
			
			dtoBillingDtList.add(dtoBillingDet);
		}
		
		/*Copy billing customer properties*/
		if (billing.getCustomer() != null) {
			CustomerDTO dtoCustomer = new CustomerDTO();
			BeanUtils.copyProperties(billing.getCustomer(), dtoCustomer);
			dtoBilling.setCustomer(dtoCustomer);
		}
		dtoBilling.setBillingItems(dtoBillingDtList);
		
		return dtoBilling;
	}
	
	public Billing copyBilling(BillingDTO billingDto) {
		/*Copy high level properties*/
		Billing mdBilling = new Billing();
		BeanUtils.copyProperties(billingDto, mdBilling);
		
		/*Copy billing detail list properties*/
		Set<BillingDetails> billingDtList = new HashSet<BillingDetails>();
		for (BillingDetailsDTO billDet : billingDto.getBillingItems()) {
			BillingDetails mdBillingDet = new BillingDetails();
			BeanUtils.copyProperties(billDet, mdBillingDet);
			mdBillingDet.setBilling(mdBilling);
			
			if (billDet.getProduct().getId() > 0) {
				Product mdProduct = new Product();
				BeanUtils.copyProperties(billDet.getProduct(), mdProduct);
				mdBillingDet.setPrduct(mdProduct);
			}
			billingDtList.add(mdBillingDet);
		}
		mdBilling.setBillingItems(billingDtList);
		
		/*Copy billing customer properties*/
		if (billingDto.getCustomer().getId() > 0) {
			Customer dtoCustomer = new Customer();
			BeanUtils.copyProperties(billingDto.getCustomer(), dtoCustomer);
			mdBilling.setCustomer(dtoCustomer);
		}
		
		
		return mdBilling;
	}
	
	public List<BillingDTO> copyBillingList(List<Billing> billingList) {
		List<BillingDTO> dtoBillingList = new ArrayList<BillingDTO>();
		for (Billing billing : billingList) {
			BillingDTO dtoBilling = copyBilling(billing);
			dtoBillingList.add(dtoBilling);
		}
		return dtoBillingList;
	}
}