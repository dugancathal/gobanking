package com.dugancathal.javabanking;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<String> productIds = new ArrayList<>();

	public Cart() {
	}

	public Cart(List<String> productIds) {
		this.productIds = productIds;
	}

	public List<String> getProductIds() {
		return productIds;
	}

	public void addProductId(String productId) {
		productIds.add(productId);
	}
}