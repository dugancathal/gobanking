package com.dugancathal.javabanking;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toSet;

@Component
public class ProductRepository {

	public Product find(String idOrName) {
		return catalog().get(idOrName);
	}

	public Collection<Product> all() {
		return catalog().values().stream().collect(toSet());
	}

	private Map<String, Product> catalog() {
		Map<String, Product> products = new HashMap<>();
		products.put("1", new Product("1", "TeddyBear", "Soft and Cuddly", new Money(999)));
		products.put("TeddyBear", new Product("1", "TeddyBear", "Soft and Cuddly", new Money(999)));
		return products;
	}
}