package com.gpware.billing.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.gpware.billing.dto.CustomerDTO;
import com.gpware.billing.model.Customer;

public class CustomerHelper implements Serializable {

	private static final long serialVersionUID = 1184324719087879804L;

	public static CustomerDTO copyCustomer(Customer cust) {
		CustomerDTO dtoCust = new CustomerDTO();
		BeanUtils.copyProperties(cust, dtoCust);
		return dtoCust;
	}

	public static List<CustomerDTO> copyBillingList(List<Customer> custList) {
		List<CustomerDTO> dtoCustList = new ArrayList<CustomerDTO>();
		for (Customer cust : custList) {
			CustomerDTO dtoCust = copyCustomer(cust);
			dtoCustList.add(dtoCust);
		}
		return dtoCustList;
	}
}