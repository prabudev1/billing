package com.gpware.billing;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.gpware.billing.model.Product;

public class ProductRestClientUtil {

	public void getProductByIdDemo(int id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:9097/product/get/" + id + "";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Product> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				Product.class, 1);
		Product product = responseEntity.getBody();
		System.out.println(
				"Id:" + product.getId() + ", Title:" + product.getName() + ", Category:" + product.getCode());
	}

	public void getAllProductsDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:9097/product/getAll";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Product[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				Product[].class);
		Product[] products = responseEntity.getBody();
		for (Product product : products) {
			System.out.println(
					"Id:" + product.getId() + ", Title:" + product.getName() + ", Category:" + product.getCode());
		}
	}

	public void addProductDemo(String name, String code) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:9097/product/add";
		Product objProduct = new Product();
		objProduct.setName(name);
		objProduct.setCode(code);
		HttpEntity<Product> requestEntity = new HttpEntity<Product>(objProduct, headers);
		URI uri = restTemplate.postForLocation(url, requestEntity);
		System.out.println(uri.getPath());
	}

	public void updateProductDemo(int id, String name, String code) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:9097/product/update";
		Product objProduct = new Product();
		objProduct.setId(id);
		objProduct.setName(name);
		objProduct.setCode(code);
		HttpEntity<Product> requestEntity = new HttpEntity<Product>(objProduct, headers);
		restTemplate.put(url, requestEntity);
	}

	public void deleteProductDemo(int id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:9097/product/delete/" + id + "";
		HttpEntity<Product> requestEntity = new HttpEntity<Product>(headers);
		restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);
	}

	public static void main(String args[]) {
		ProductRestClientUtil util = new ProductRestClientUtil();
		// util.addProductDemo("New Product", "CODE-NEW-1");

		util.getAllProductsDemo();
		// util.getProductByIdDemo(4);
		// util.updateProductDemo(6, "New Product Updated", "CODE-NEW-2");
		// util.getProductByIdDemo(5);
		// util.deleteProductDemo(6);
	}
}
