package com.gpware.billing.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.gpware.billing.model.Customer;

@Transactional
@Repository
public class CustomerDAO implements ICustomerDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Customer getCustomerById(int customerId) {
		return entityManager.find(Customer.class, customerId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAllCustomers(String userIdentifier, String serchVal, String orderBY) {
		if (serchVal != null && !serchVal.equals("")) {
			serchVal = "%" + serchVal + "%";
		} else {
			serchVal = "%";
		}
		String hql = "FROM Customer as cust where cust.createdBy = ? and (cust.name like ? or cust.mobile like ? )";
		if (orderBY != null) {
			if (orderBY.equalsIgnoreCase("1")) {
				hql += " order by cust.name asc ";
			} else if (orderBY.equalsIgnoreCase("2")) {
				hql += " order by cust.email asc ";
			} else if (orderBY.equalsIgnoreCase("3")) {
				hql += " order by cust.createdOn asc ";
			} else if (orderBY.equalsIgnoreCase("4")) {
				hql += " order by cust.name desc ";
			} else if (orderBY.equalsIgnoreCase("5")) {
				hql += " order by cust.email desc ";
			} else if (orderBY.equalsIgnoreCase("6")) {
				hql += " order by cust.createdOn desc ";
			}
		}
		return (List<Customer>) entityManager.createQuery(hql)
				.setParameter(0, userIdentifier)
				.setParameter(1, serchVal)
				.setParameter(2, serchVal).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomersByNameOrMobile(String userIdentifier, String serchVal) {
		serchVal = "%" + serchVal + "%";
		String hql = "FROM Customer as cust where cust.createdBy = ? and (cust.name like ? or cust.mobile like ?)";
		return (List<Customer>) entityManager.createQuery(hql)
				.setParameter(0, userIdentifier)
				.setParameter(1, serchVal)
				.setParameter(2, serchVal).getResultList();
	}

	@Override
	public void addCustomer(Customer customer) {
		entityManager.persist(customer);
	}

	@Override
	public void updateCustomer(Customer dbCustRec, Customer newCustomer) {
		entityManager.merge(newCustomer);
	}

	@Override
	public void deleteCustomer(int customerId) {
		entityManager.remove(getCustomerById(customerId));
	}

	@Override
	public boolean customerExists(String userIdentifier, String newMobileNo, String prevMobileNo) {
		String hql = "FROM Customer as cust WHERE cust.createdBy = ? and (cust.mobile = ? and cust.mobile <> ?)";
		int count = entityManager.createQuery(hql)
				.setParameter(0, userIdentifier)
				.setParameter(1, newMobileNo)
				.setParameter(2, prevMobileNo).getResultList().size();
		return count > 0 ? true : false;
	}
}
