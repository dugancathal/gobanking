package com.dugancathal.javabanking;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Bank {
	private final String id;
	private Money holdings = new Money(0);

	@JsonCreator
	public Bank(@JsonProperty("id") String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public Money getHoldings() {
		return holdings;
	}

	public void deposit(Money money) {
		holdings = holdings.add(money);
	}

	public void withdraw(Money money) {
		deposit(new Money(-money.getPennies()));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Bank bank = (Bank) o;

		boolean idsEqual = id != null ? id.equals(bank.id) : bank.id == null;
		boolean holdingsEqual = holdings.equals(bank.getHoldings());
		return idsEqual && holdingsEqual;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
