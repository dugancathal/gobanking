package com.dugancathal.javabanking;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Money {
	private final int pennies;
	private final String currency = "USD";

	@JsonCreator
	public Money(@JsonProperty("money") float amount) {
		this.pennies = (int) (amount * 100);
	}

	public Money(int pennies) {
		this.pennies = pennies;
	}

	@JsonIgnore
	public int getPennies() {
		return pennies;
	}

	public double getMoney() {
		return pennies / 100.0;
	}

	public String getCurrency() {
		return currency;
	}

	public Money add(Money toAdd) {
		return new Money(getPennies() + toAdd.getPennies());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Money money = (Money) o;

		if (pennies != money.pennies) return false;
		return currency.equals(money.currency);

	}

	@Override
	public int hashCode() {
		int result = pennies;
		result = 31 * result + currency.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Money{" +
				"pennies=" + pennies +
				", currency='" + currency + '\'' +
				'}';
	}
}