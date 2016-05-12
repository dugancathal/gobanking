package com.dugancathal.javabanking;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CartController {
	private Cart cart = new Cart();

	@RequestMapping(path = "/cart/items", method = RequestMethod.POST)
	public Cart addItem(@RequestBody Map<String, String> body) {
		cart.addProductId(body.get("product_id"));
		return cart;
	}

	@RequestMapping(path = "/cart/items", method = RequestMethod.GET)
	public List<String> getItemIds() {
		return cart.getProductIds();
	}
}