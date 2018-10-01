package com.gpware.billing.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpware.billing.dao.IProductDAO;
import com.gpware.billing.model.Product;

@Service
public class ProductService implements IProductService {
	@Autowired
	private IProductDAO productDAO;
	 
	@Override
	public Product getProductById(int productId) {
		Product obj = productDAO.getProductById(productId);
		return obj;
	}

	@Override
	public List<Product> getAllProducts(String userIdentifier, String serchVal, String orderBy) {
		return productDAO.getAllProducts(userIdentifier, serchVal, orderBy);
	}

	@Override
	public List<Product> getProductsByNameOrCode(String userIdentifier, String serch) {
		return productDAO.getProductsByNameOrCode(userIdentifier, serch);
	}

	@Override
	public synchronized boolean addProduct(String userIdentifier, Product product) {
		if (productDAO.productExists(userIdentifier, product.getCode(), "")) {
			return false;
		} else {
			productDAO.addProduct(product);
			return true;
		}
	}

	@Override
	public boolean updateProduct(String userIdentifier, Product newProduct) {
		Product dbPrdRec = getProductById(newProduct.getId());
		if (productDAO.productExists(userIdentifier, newProduct.getCode(), dbPrdRec.getCode())) {
			return false;
		} else {
			newProduct.setCreatedBy(dbPrdRec.getCreatedBy());
			newProduct.setLastUpdatedBy(userIdentifier);
			productDAO.updateProduct(dbPrdRec, newProduct);
			return true;
		}		
	}

	@Override
	public void deleteProduct(int productId) {
		productDAO.deleteProduct(productId);
	}

}
