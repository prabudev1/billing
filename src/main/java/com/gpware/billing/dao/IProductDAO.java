package com.gpware.billing.dao;

import java.util.List;

import com.gpware.billing.model.Product;

public interface IProductDAO {
	List<Product> getAllProducts(String serchVal, String orderBy);
	
	List<Product> getProductsByNameOrCode(String serchVal);
	
	Product getProductById(int productId);

	void addProduct(Product product);

	void updateProduct(Product dbCustRec, Product newProduct);

	void deleteProduct(int productId);
	
	boolean productExists(String name, String code);
}
