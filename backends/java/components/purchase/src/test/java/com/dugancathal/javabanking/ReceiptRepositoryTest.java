package com.dugancathal.javabanking;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReceiptRepositoryTest {
	private ReceiptRepository receiptRepository;
	private Money subtotal = new Money(0);
	private Money tax = new Money(0);
	private Money total = new Money(0);

	@Before
	public void setup() { receiptRepository = new ReceiptRepository(); }
	
	@Test
	public void canCreateReceiptsWithIds() {
		Receipt newReceipt = receiptRepository.create(subtotal, tax, total);
		Receipt newReceipt2 = receiptRepository.create(subtotal, tax, total);

		assertNotEquals(newReceipt, newReceipt2);
	}

	@Test
	public void canRetrieveCreatedReceipts() {
		Receipt newReceipt = receiptRepository.create(subtotal, tax, total);

		assertEquals(newReceipt, receiptRepository.find(newReceipt.getId()));
	}

	@Test
	public void receiptsPersist() {
		Receipt newReceipt = receiptRepository.create(subtotal, tax, total);

		newReceipt.setTotal(new Money(100));

		assertEquals(newReceipt, receiptRepository.find(newReceipt.getId()));
	}
}