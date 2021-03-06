package com.gpware.billing.dao;

import java.util.List;

import com.gpware.billing.model.Customer;

public interface ICustomerDAO {
	List<Customer> getAllCustomers(String userIdentifier, String searchVal, String orderBY);

	List<Customer> getCustomersByNameOrMobile(String userIdentifier, String serch);

	Customer getCustomerById(int customerId);

	void addCustomer(Customer customer);

	void updateCustomer(Customer dbCustRec, Customer newCustomer);

	void deleteCustomer(int customerId);

	boolean customerExists(String userIdentifier, String newMobileNo, String prevMobileNo);

}
