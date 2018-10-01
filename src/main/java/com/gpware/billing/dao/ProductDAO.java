package com.gpware.billing.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.gpware.billing.model.Product;

@Transactional
@Repository
public class ProductDAO implements IProductDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Product getProductById(int productId) {
		return entityManager.find(Product.class, productId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts(String userIdentifier, String serchVal, String orderBy) {
		if (serchVal != null && !serchVal.equals("")) {
			serchVal = "%" + serchVal + "%";
		} else {
			serchVal = "%";
		}
		String hql = "FROM Product as prd where prd.createdBy = ? and (prd.name like ? or prd.code like ? )";
		if (orderBy != null) {
			if (orderBy.equalsIgnoreCase("1")) {
				hql += " order by prd.name asc ";
			} else if (orderBy.equalsIgnoreCase("2")) {
				hql += " order by prd.name desc ";
			} else if (orderBy.equalsIgnoreCase("3")) {
				hql += " order by prd.code asc ";
			} else if (orderBy.equalsIgnoreCase("4")) {
				hql += " order by prd.code desc ";
			} else if (orderBy.equalsIgnoreCase("5")) {
				hql += " order by prd.value asc ";
			} else if (orderBy.equalsIgnoreCase("6")) {
				hql += " order by prd.value desc ";
			} else if (orderBy.equalsIgnoreCase("7")) {
				hql += " order by prd.createdOn asc ";
			} else if (orderBy.equalsIgnoreCase("8")) {
				hql += " order by prd.createdOn desc ";
			}
		}
		return (List<Product>) entityManager.createQuery(hql)
				.setParameter(0, userIdentifier)
				.setParameter(1, serchVal)
				.setParameter(2, serchVal).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductsByNameOrCode(String userIdentifier, String serchVal) {
		serchVal = "%" + serchVal + "%";
		String hql = "FROM Product as prd where prd.createdBy = ? and (prd.name like ? or prd.code like ?)";
		return (List<Product>) entityManager.createQuery(hql)
				.setParameter(0, userIdentifier)
				.setParameter(1, serchVal)
				.setParameter(2, serchVal).getResultList();
	}
	
	@Override
	public void addProduct(Product product) {
		entityManager.persist(product);
	}

	@Override
	public void updateProduct(Product dbCustRec, Product newProduct) {
		entityManager.merge(newProduct);
	}

	@Override
	public void deleteProduct(int productId) {
		entityManager.remove(getProductById(productId));
	}
	
	@Override
	public boolean productExists(String userIdentifier, String newCode, String prevCode) {
		String hql = "FROM Product as prd WHERE prd.createdBy = ? and (prd.code = ? and prd.code <> ?)";
		int count = entityManager.createQuery(hql)
				.setParameter(0, userIdentifier)
				.setParameter(1, newCode)
				.setParameter(2, prevCode).getResultList().size();
		return count > 0 ? true : false;
	}

}
