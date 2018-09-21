package com.gpware.billing;

import java.math.BigDecimal;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.gpware.billing.dto.BillingDTO;
import com.gpware.billing.dto.BillingDetailsDTO;
import com.gpware.billing.dto.CustomerDTO;
import com.gpware.billing.dto.ProductDTO;
import com.gpware.billing.model.Billing;

public class BillingRestClientUtil {

	public void getBillingByIdDemo(int id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:9097/billing/get/" + id + "";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Billing> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Billing.class, 1);
		Billing billing = responseEntity.getBody();
		System.out.println("Id:" + billing.getId() + ", Cust Id:" + billing.getCustomer().getName() + ", Value:" + billing.getTotValue());
	}

	public void getAllBillingsDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:9097/billing/getAll";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Billing[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Billing[].class);
		Billing[] billings = responseEntity.getBody();
		for (Billing billing : billings) {
			System.out.println("Id:" + billing.getId() + ", Cust Id:" + billing.getCustomer().getName() + ", Value:" + billing.getTotValue());
		}
	}

	public void addBillingDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:9097/billing/add";

		BillingDTO objBilling = new BillingDTO();
		objBilling.setTotItems(new BigDecimal(10));
		objBilling.setTotValue(new BigDecimal(1500));

		CustomerDTO cust = new CustomerDTO();
		cust.setId(2);
		objBilling.setCustomer(cust);

		Set<BillingDetailsDTO> bdList = new HashSet<BillingDetailsDTO>();
		BillingDetailsDTO bd = new BillingDetailsDTO();

		ProductDTO prd = new ProductDTO();
		prd.setId(4);
		bd.setProduct(prd);
		bd.setValue(new BigDecimal(5000));
		bdList.add(bd);
		objBilling.setBillingItems(bdList);

		HttpEntity<BillingDTO> requestEntity = new HttpEntity<BillingDTO>(objBilling, headers);
		URI uri = restTemplate.postForLocation(url, requestEntity);
		System.out.println(uri.getPath());
	}

	public void deleteBillingDemo(int id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:9097/billing/delete/" + id + "";
		HttpEntity<Billing> requestEntity = new HttpEntity<Billing>(headers);
		restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);
	}

	public static void main(String args[]) {
		BillingRestClientUtil util = new BillingRestClientUtil();
		// util.addBillingDemo();

		// util.getAllBillingsDemo();
		// util.getBillingByIdDemo(1);
		util.deleteBillingDemo(14);
	}
}
