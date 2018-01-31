package com.dugancathal.javabanking;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class ReceiptRepository {

	private Map<String, Receipt> receiptMap = new HashMap<>();
	private Integer id = 0;

	public Receipt create(Money subtotal, Money tax, Money total) {
		id++;
		Receipt receipt = new Receipt(id.toString());
		receiptMap.put(receipt.getId(), receipt);
		receipt.setSubtotal(subtotal);
		receipt.setTax(tax);
		receipt.setTotal(total);
		return receipt;

	}

	public Receipt find(String id) {
		return receiptMap.get(id);
	}


}