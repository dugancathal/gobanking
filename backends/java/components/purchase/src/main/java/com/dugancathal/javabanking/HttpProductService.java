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
import java.util.Map;

@Component
public class HttpProductService implements ProductService {
	@Autowired
	private Environment env;
	private OkHttpClient httpClient = new OkHttpClient();
	private ObjectMapper jsonMapper = new ObjectMapper();

	@Override
	public Money getProductPrice(String productId) {
		Request request = new Request.Builder()
				.url(String.format("%s/products/%s", url(), productId))
				.get()
				.build();
		try {
			Response response = httpClient.newCall(request).execute();

			Map<String, Object> body = jsonMapper.readValue(response.body().string(), new TypeReference<Map<String, Object>>() {});
			Map<?, ?> priceMap = (Map<?, ?>) body.get("price");
			int pennies = (int) ((Double)priceMap.get("money") * 100);
			return new Money(pennies);
		} catch (IOException e) {
			return null;
		}
	}

	private String url() {
		if(env.getProperty("PRODUCT_URL") != null) {
			return env.getProperty("PRODUCT_URL");
		} else {
			return "http://products.dev:8080";
		}
	}
}