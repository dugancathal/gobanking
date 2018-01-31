package com.dugancathal.javabanking;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

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
		Receipt newReceipt = receiptRepository.create(subtotal, tax, total, Collections.emptyList());
		Receipt newReceipt2 = receiptRepository.create(subtotal, tax, total, Collections.emptyList());

		assertNotEquals(newReceipt, newReceipt2);
	}

	@Test
	public void canRetrieveCreatedReceipts() {
		Receipt newReceipt = receiptRepository.create(subtotal, tax, total, Collections.emptyList());

		assertEquals(newReceipt, receiptRepository.find(newReceipt.getId()));
	}

	@Test
	public void receiptsPersist() {
		Product product = new Product("13", "hi", "there", new Money(0));
		Receipt newReceipt = receiptRepository.create(subtotal, tax, total, Collections.singletonList(product));

		Receipt receipt = receiptRepository.find(newReceipt.getId());

		assertEquals(newReceipt, receipt);
		assertEquals(receipt.getSubtotal(), subtotal);
		assertEquals(receipt.getTax(), tax);
		assertEquals(receipt.getTotal(), total);
		assertTrue(receipt.getProducts().contains(product));
	}
}