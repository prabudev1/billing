package com.gpware.billing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gpware.billing.model.Customer;
import com.gpware.billing.services.ICustomerService;

@Controller
@RequestMapping("customer")
@CrossOrigin
public class CustomerController {
	@Autowired
	private ICustomerService customerService;

	@GetMapping("get/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer id) {
		Customer customer = customerService.getCustomerById(id);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@GetMapping("getSearchList")
	public ResponseEntity<List<Customer>> getCustomersByNameOrMobile(@RequestParam("q") String serchVal) {
		List<Customer> customerList = customerService.getCustomersByNameOrMobile(serchVal);
		return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK);
	}

	@GetMapping("getAll")
	@CrossOrigin
	public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam("q") String serchVal, @RequestParam("o") String orderBy) {
		List<Customer> list = customerService.getAllCustomers(serchVal, orderBy);
		return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping("add")
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
		boolean flag = customerService.addCustomer(customer);
		if (flag == false) {
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(customer.getId() + "", HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping("update")
	public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
		boolean flag = customerService.updateCustomer(customer);
		if (flag == false) {
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(customer.getId() + "", HttpStatus.OK);

	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Integer id) {
		customerService.deleteCustomer(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}