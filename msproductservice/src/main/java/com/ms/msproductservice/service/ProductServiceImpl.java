package com.ms.msproductservice.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.msproductservice.entities.Product;
import com.ms.msproductservice.exception.ProductServiceCustomException;
import com.ms.msproductservice.model.ProductRequest;
import com.ms.msproductservice.model.ProductResponse;
import com.ms.msproductservice.repos.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public long addProduct(ProductRequest productRequest) {
		log.info("adding product..");
		
		Product product = Product.builder().productName(productRequest.getName())
				.price(productRequest.getPrice())
				.quantity(productRequest.getQuantity()).build();
		
		Product productCreated= productRepository.save(product);
		return productCreated.getProductId();
	}

	@Override
	public ProductResponse getProduct(long productId) {
		log.info("get product for product id :{}",productId);
		
		Product product = productRepository.findById(productId)
				.orElseThrow(()->new ProductServiceCustomException("product not found","PRODCUT_NOT_FOUND"));
		
		ProductResponse productResponse= new ProductResponse();
		BeanUtils.copyProperties(product, productResponse);
		return productResponse;
	}

	@Override
	public void reduceQuantity(long productId, long quantity) {

		Product product = productRepository.findById(productId)
				.orElseThrow(()->new ProductServiceCustomException("product not found","PRODCUT_NOT_FOUND"));
	
	    if(product.getQuantity()<quantity) {
	    	throw new ProductServiceCustomException("Product does not enough quantity","INSUFFICIENT_QUANTITY");
	    }
	    
	    product.setQuantity(product.getQuantity()-quantity);
	    
	    productRepository.save(product);
	    log.info("product quantity updated successfully");
	    
	}

}
