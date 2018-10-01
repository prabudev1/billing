package com.gpware.billing.services;

import java.util.List;

import com.gpware.billing.model.Product;

public interface IProductService {

	List<Product> getAllProducts(String userIdentifier, String serchVal, String orderBy);

	List<Product> getProductsByNameOrCode(String userIdentifier, String serch);

	Product getProductById(int productId);

	boolean addProduct(String userIdentifier, Product product);

	boolean updateProduct(String userIdentifier, Product product);

	void deleteProduct(int productId);

}
