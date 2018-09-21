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
	public List<Product> getAllProducts(String serchVal, String orderBy) {
		return productDAO.getAllProducts(serchVal, orderBy);
	}

	@Override
	public List<Product> getProductsByNameOrCode(String serch) {
		return productDAO.getProductsByNameOrCode(serch);
	}

	@Override
	public synchronized boolean addProduct(Product product) {
		if (productDAO.productExists(product.getCode(), "")) {
			return false;
		} else {
			productDAO.addProduct(product);
			return true;
		}
	}

	@Override
	public boolean updateProduct(Product newProduct) {
		Product dbPrdRec = getProductById(newProduct.getId());
		if (productDAO.productExists(newProduct.getCode(), dbPrdRec.getCode())) {
			return false;
		} else {
			productDAO.updateProduct(dbPrdRec, newProduct);
			return true;
		}		
	}

	@Override
	public void deleteProduct(int productId) {
		productDAO.deleteProduct(productId);
	}

}
