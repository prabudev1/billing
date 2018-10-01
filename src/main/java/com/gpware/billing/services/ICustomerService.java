package com.gpware.billing.services;

import java.util.List;

import com.gpware.billing.model.Customer;

public interface ICustomerService {
	
	List<Customer> getAllCustomers(String userIdentifier, String searchVal, String orderBY);

	List<Customer> getCustomersByNameOrMobile(String userIdentifier, String serch);
	
	Customer getCustomerById(int customerId);

	boolean addCustomer(String userIdentifier, Customer customer);

	boolean updateCustomer(String userIdentifier, Customer customer);

	void deleteCustomer(int customerId);

}
