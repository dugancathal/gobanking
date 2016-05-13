package com.dugancathal.javabanking;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;

@Component
public class HttpCartService implements CartService {
	@Autowired
	private Environment env;
	private RestTemplate httpClient = new RestTemplate();
	private ObjectMapper jsonMapper = new ObjectMapper();

	@Override
	public List<String> getItemIds() {
		String responseBody = httpClient.getForObject(String.format("%s/cart/items", url()), String.class);
		List<String> itemIds = emptyList();
		try {
			itemIds = jsonMapper.readValue(responseBody, new TypeReference<List<String>>(){});
		} catch (IOException e) {}
		return itemIds;
	}

	private String url() {
		if(env.getProperty("CART_URL") != null) {
			return env.getProperty("CART_URL");
		} else {
			return "http://cart.dev:8080";
		}
	}
}