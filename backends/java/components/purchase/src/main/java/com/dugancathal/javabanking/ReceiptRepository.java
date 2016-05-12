package com.dugancathal.javabanking;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class ReceiptRepository {

	private Map<String, Receipt> receiptMap = new HashMap<>();

	public Receipt create(Money subtotal, Money tax, Money total) {
		Receipt receipt = new Receipt(nextId());
		receiptMap.put(receipt.getId(), receipt);
		receipt.setSubtotal(subtotal);
		receipt.setTax(tax);
		receipt.setTotal(total);
		return receipt;

	}

	public Receipt find(String id) {
		return receiptMap.get(id);
	}

	private String nextId() {
		return Integer.toString(new Random().nextInt());
	}
}