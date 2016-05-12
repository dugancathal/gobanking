package com.dugancathal.javabanking;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
	@Test
	public void canMakeDeposits() {
		Bank bank = new Bank("foo");
		bank.deposit(new Money(100));
		assertEquals(100, bank.getHoldings().getPennies());
	}
}