package com.gpware.billing.controller;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gpware.billing.model.Product;
import com.gpware.billing.services.IProductService;

@Controller
@RequestMapping("product")
@CrossOrigin
public class ProductController {
	@Autowired
	private IProductService productService;

	@GetMapping("get/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
		Product product = productService.getProductById(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@GetMapping("getAll")
	@CrossOrigin
	public ResponseEntity<List<Product>> getAllProducts(Principal principal, @RequestParam("q") String serchVal, @RequestParam("o") String orderBy) {
		List<Product> list = productService.getAllProducts(principal.getName(), serchVal, orderBy);
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}

	@GetMapping("getSearchList")
	public ResponseEntity<List<Product>> getCustomersByNameOrMobile(Principal principal, @RequestParam("q") String serchVal) {
		List<Product> customerList = productService.getProductsByNameOrCode(principal.getName(), serchVal);
		return new ResponseEntity<List<Product>>(customerList, HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping("add")
	public ResponseEntity<String> addProduct(Principal principal, @RequestBody Product product) {
		product.setCreatedBy(principal.getName());
		boolean flag = productService.addProduct(principal.getName(), product);
		if (flag == false) {
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(product.getId() + "", HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("update")
	public ResponseEntity<String> updateProduct(Principal principal, @RequestBody Product product) {
		boolean flag = productService.updateProduct(principal.getName(), product);
		if (flag == false) {
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(product.getId() + "", HttpStatus.OK);

	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id) {
		productService.deleteProduct(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}