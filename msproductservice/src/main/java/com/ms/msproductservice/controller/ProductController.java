package com.ms.msproductservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.msproductservice.entities.Product;
import com.ms.msproductservice.model.ProductRequest;
import com.ms.msproductservice.model.ProductResponse;
import com.ms.msproductservice.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	

	@Autowired
	ProductService productService;
	
	@PostMapping
	public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
		long productId = productService.addProduct(productRequest);
		return new ResponseEntity<Long>(productId, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long productId){
		ProductResponse response = productService.getProduct(productId);
		return new ResponseEntity<ProductResponse>(response, HttpStatus.OK);		
	}
	
	@PutMapping("/reduceQuantity/{id}")
	public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId,@RequestParam long quantity){
		productService.reduceQuantity(productId,quantity);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
