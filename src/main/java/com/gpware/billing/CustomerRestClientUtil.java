package com.gpware.billing;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.gpware.billing.model.Customer;

public class CustomerRestClientUtil {

	public void getCustomerByIdDemo(int id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:9097/customer/get/" + id +"";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Customer> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				Customer.class, 1);
		Customer customer = responseEntity.getBody();
		System.out.println(
				"Id:" + customer.getId() + ", Title:" + customer.getName() + ", Category:" + customer.getMobile());
	}

	public void getAllCustomersDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:9097/customer/getAll";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Customer[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				Customer[].class);
		Customer[] customers = responseEntity.getBody();
		for (Customer customer : customers) {
			System.out.println(
					"Id:" + customer.getId() + ", Title:" + customer.getName() + ", Category:" + customer.getMobile());
		}
	}

	public void addCustomerDemo(String name, String mobileNo) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:9097/customer/add";
		Customer objCustomer = new Customer();
		objCustomer.setName(name);
		objCustomer.setMobile(mobileNo);
		HttpEntity<Customer> requestEntity = new HttpEntity<Customer>(objCustomer, headers);
		URI uri = restTemplate.postForLocation(url, requestEntity);
		System.out.println(uri.getPath());
	}

	public void updateCustomerDemo(int id, String name, String mobileNo) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:9097/customer/update";
		Customer objCustomer = new Customer();
		objCustomer.setId(id);
		objCustomer.setName(name);
		objCustomer.setMobile(mobileNo);
		HttpEntity<Customer> requestEntity = new HttpEntity<Customer>(objCustomer, headers);
		restTemplate.put(url, requestEntity);
	}

	public void deleteCustomerDemo(int id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:9097/customer/delete/" + id + "";
		HttpEntity<Customer> requestEntity = new HttpEntity<Customer>(headers);
		restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);
	}

	public static void main(String args[]) {
		CustomerRestClientUtil util = new CustomerRestClientUtil();
		// util.addCustomerDemo("New Prabu - 1", "00000000");
	
		// util.getAllCustomersDemo();
		// util.getCustomerByIdDemo(4);
		util.updateCustomerDemo(1, "John Dalton", "956232312");
		// util.getCustomerByIdDemo(5);
		// util.deleteCustomerDemo(5);
	}
}
