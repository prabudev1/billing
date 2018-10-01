package com.gpware.billing.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpware.billing.dao.ICustomerDAO;
import com.gpware.billing.model.Customer;

@Service
public class CustomerService implements ICustomerService {
	@Autowired
	private ICustomerDAO customerDAO;
	 
	@Override
	public Customer getCustomerById(int customerId) {
		Customer obj = customerDAO.getCustomerById(customerId);
		return obj;
	}

	@Override
	public List<Customer> getAllCustomers(String userIdentifier, String searchVal, String orderBY) {
		return customerDAO.getAllCustomers(userIdentifier, searchVal, orderBY);
	}
	
	@Override
	public List<Customer> getCustomersByNameOrMobile(String userIdentifier, String serch) {
		return customerDAO.getCustomersByNameOrMobile(userIdentifier, serch);
	}

	@Override
	public synchronized boolean addCustomer(String userIdentifier, Customer customer) {
		if (customerDAO.customerExists(userIdentifier, customer.getMobile(), "")) {
			return false;
		} else {
			customerDAO.addCustomer(customer);
			return true;
		}
	}

	@Override
	public boolean updateCustomer(String userIdentifier, Customer newCustomer) {
		Customer dbCustRec = getCustomerById(newCustomer.getId());
		if (customerDAO.customerExists(userIdentifier, newCustomer.getMobile(), dbCustRec.getMobile())) {
			return false;
		} else {
			newCustomer.setCreatedBy(dbCustRec.getCreatedBy());
			newCustomer.setLastUpdatedBy(userIdentifier);
			customerDAO.updateCustomer(dbCustRec, newCustomer);
			return true;
		}
	}

	@Override
	public void deleteCustomer(int customerId) {
		customerDAO.deleteCustomer(customerId);
	}
}
