package com.dugancathal.javabanking;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BankRepositoryTest {
	private BankRepository bankRepository;

	@Before
	public void setup() {
		bankRepository = new BankRepository();
	}

	@Test
	public void canCreateBanksWithIds() {
		Bank newBank = bankRepository.create();
		Bank newBank2 = bankRepository.create();

		assertNotEquals(newBank.getId(), newBank2.getId());
	}

	@Test
	public void canRetrieveCreatedBanks() {
		Bank newBank = bankRepository.create();

		Bank retrievedBank = bankRepository.find(newBank.getId());

		assertEquals(newBank, retrievedBank);
	}

	@Test
	public void bankAmountsStartAtZero() {
		Bank bank = bankRepository.create();
		assertEquals(0, bank.getHoldings().getPennies());
	}

	@Test
	public void bankAmountsPersist() {
		Bank bank = bankRepository.create();
		bank.deposit(new Money(100));

		assertEquals(bank, bankRepository.find(bank.getId()));
	}
}