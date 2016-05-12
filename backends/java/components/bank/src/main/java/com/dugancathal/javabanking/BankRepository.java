package com.dugancathal.javabanking;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class BankRepository {
	private Map<String, Bank> bankMap = new HashMap<>();

	public Bank create() {
		Bank bank = new Bank(Integer.toString(new Random().nextInt()));
		bankMap.put(bank.getId(), bank);
		return bank;
	}

	public Bank find(String id) {
		return bankMap.get(id);
	}
}