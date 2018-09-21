package com.gpware.billing.services;

import java.util.List;

import com.gpware.billing.model.Customer;

public interface ICustomerService {
	
	List<Customer> getAllCustomers(String searchVal, String orderBY);

	List<Customer> getCustomersByNameOrMobile(String serch);
	
	Customer getCustomerById(int customerId);

	boolean addCustomer(Customer customer);

	boolean updateCustomer(Customer customer);

	void deleteCustomer(int customerId);

}
