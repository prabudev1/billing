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
	public List<Customer> getAllCustomers(String searchVal, String orderBY) {
		return customerDAO.getAllCustomers(searchVal, orderBY);
	}
	
	@Override
	public List<Customer> getCustomersByNameOrMobile(String serch) {
		return customerDAO.getCustomersByNameOrMobile(serch);
	}

	@Override
	public synchronized boolean addCustomer(Customer customer) {
		if (customerDAO.customerExists(customer.getMobile(), "")) {
			return false;
		} else {
			customerDAO.addCustomer(customer);
			return true;
		}
	}

	@Override
	public boolean updateCustomer(Customer newCustomer) {
		Customer dbCustRec = getCustomerById(newCustomer.getId());
		if (customerDAO.customerExists(newCustomer.getMobile(), dbCustRec.getMobile())) {
			return false;
		} else {
			customerDAO.updateCustomer(dbCustRec, newCustomer);
			return true;
		}
	}

	@Override
	public void deleteCustomer(int customerId) {
		customerDAO.deleteCustomer(customerId);
	}
}
