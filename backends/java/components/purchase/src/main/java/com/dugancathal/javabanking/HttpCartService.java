package com.dugancathal.javabanking;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;

@Component
public class HttpCartService implements CartService {
	private RestTemplate httpClient = new RestTemplate();
	private ObjectMapper jsonMapper = new ObjectMapper();

	@Override
	public List<String> getItemIds() {
		String responseBody = httpClient.getForObject("http://localhost:8080/cart/items", String.class);
		List<String> itemIds = emptyList();
		try {
			itemIds = jsonMapper.readValue(responseBody, new TypeReference<List<String>>(){});
		} catch (IOException e) {}
		return itemIds;
	}
}