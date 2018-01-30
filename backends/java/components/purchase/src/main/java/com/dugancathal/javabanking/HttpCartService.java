package com.dugancathal.javabanking;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;

@Component
public class HttpCartService implements CartService {
	@Autowired
	private Environment env;
	private OkHttpClient httpClient = new OkHttpClient();
	private ObjectMapper jsonMapper = new ObjectMapper();

	@Override
	public List<String> getItemIds() {
		Request request = new Request.Builder()
				.url(String.format("%s/cart/items", url()))
				.get()
				.build();
		List<String> itemIds = emptyList();
		try {
			Response response = httpClient.newCall(request).execute();
			itemIds = jsonMapper.readValue(response.body().string(), new TypeReference<List<String>>(){});
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