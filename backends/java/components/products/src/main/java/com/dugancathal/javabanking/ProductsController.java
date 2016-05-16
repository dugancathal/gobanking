package com.dugancathal.javabanking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ProductsController {
	@Autowired
	private ProductRepository productRepository;

	@RequestMapping(path="/products", method= RequestMethod.GET)
	public Collection<Product> getProducts() {
		return productRepository.all();
	}

	@RequestMapping(path="/products/{id}", method= RequestMethod.GET)
	public Product getProduct(@PathVariable String id) {
		return productRepository.find(id);
	}
}