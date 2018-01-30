package com.dugancathal.javabanking;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HttpBankService implements BankService {
	@Autowired
	private Environment env;
	private OkHttpClient httpClient = new OkHttpClient();

	@Override
	public void makeWithdrawal(String accountId, Money amount) {
		try {
			Request request = new Request.Builder()
					.url(String.format("%s/banks/%s/withdrawal", url(), accountId))
					.post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
							new ObjectMapper().writeValueAsString(amount)))
					.build();
			httpClient.newCall(request).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String url() {
		if(env.getProperty("BANK_URL") != null) {
			return env.getProperty("BANK_URL");
		} else {
			return "http://bank.dev:8080";
		}
	}
}
