package com.dugancathal.javabanking;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpBankService implements BankService {
	private RestTemplate httpClient = new RestTemplate();

	@Override
	public void makeWithdrawal(String accountId, Money amount) {
		httpClient.postForObject(String.format("http://localhost:8080/banks/%s/withdrawal", accountId), amount, String.class);
	}
}
