package com.dugancathal.javabanking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpBankService implements BankService {
	@Autowired
	private Environment env;
	private RestTemplate httpClient = new RestTemplate();

	@Override
	public void makeWithdrawal(String accountId, Money amount) {
		httpClient.postForObject(String.format("%s/banks/%s/withdrawal", url(), accountId), amount, String.class);
	}

	private String url() {
		if(env.getProperty("BANK_URL") != null) {
			return env.getProperty("BANK_URL");
		} else {
			return "http://bank.dev:8080";
		}
	}
}
