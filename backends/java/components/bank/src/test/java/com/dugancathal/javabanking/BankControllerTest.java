package com.dugancathal.javabanking;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class BankControllerTest {
	private BankController controller;
	private BankRepository bankRepository;
	@Before
	public void setup() {
		bankRepository = new BankRepository();
		controller = new BankController(bankRepository);
	}

	@Test
	public void banksStartWithZeroDollars() {
		Bank bank = controller.createAccount();
		Money amount = controller.getFunds(bank.getId());
		assertEquals(0, amount.getPennies());
	}

	@Test
	public void makingADepositAddsMoneyToABank() {
		Bank bank = controller.createAccount();
		Bank responseBank = controller.depositMoney(bank.getId(), new Money(200));
		assertEquals(bank.getId(), responseBank.getId());
		Money amount = controller.getFunds(bank.getId());

		assertEquals(amount.getPennies(), 200);
	}

	@Test
	public void withdrawingFundsRemovesMoneyFromABank() {
		Bank bank = controller.createAccount();
		controller.depositMoney(bank.getId(), new Money(1000));

		controller.withdrawFunds(bank.getId(), new Money(400));

		assertEquals(600, bank.getHoldings().getPennies());
	}
}