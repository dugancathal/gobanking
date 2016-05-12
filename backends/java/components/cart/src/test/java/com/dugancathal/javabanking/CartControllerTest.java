package com.dugancathal.javabanking;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CartControllerTest {
	private CartController controller;

	@Before
	public void setup() {
		controller = new CartController();
	}

	@Test
	public void canRetrieveAddedItems() {
		Map<String,String> body = new HashMap<>();
		body.put("product_id", "fancy-id");
		controller.addItem(body);

		assertEquals(Arrays.asList("fancy-id"), controller.getItemIds());
	}
}