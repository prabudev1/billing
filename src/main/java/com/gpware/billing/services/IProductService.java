package com.gpware.billing.services;

import java.util.List;

import com.gpware.billing.model.Product;

public interface IProductService {

	List<Product> getAllProducts(String serchVal, String orderBy);

	List<Product> getProductsByNameOrCode(String serch);

	Product getProductById(int productId);

	boolean addProduct(Product product);

	boolean updateProduct(Product product);

	void deleteProduct(int productId);

}
