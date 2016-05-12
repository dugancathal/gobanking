package com.dugancathal.javabanking;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Receipt {
	private final String id;
	private Money subtotal;
	private Money tax;
	private Money total;

	@JsonCreator
	public Receipt(@JsonProperty("id") String id,
				   @JsonProperty("subtotal") Money subtotal,
				   @JsonProperty("tax") Money tax,
				   @JsonProperty("total") Money total) {
		this.id = id;
		this.subtotal = subtotal;
		this.tax = tax;
		this.total = total;
	}

	public Receipt(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public Money getSubtotal() {
		return subtotal;
	}

	public Money getTax() {
		return tax;
	}

	public Money getTotal() {
		return total;
	}

	public void setSubtotal(Money subtotal) {
		this.subtotal = subtotal;
	}

	public void setTax(Money tax) {
		this.tax = tax;
	}

	public void setTotal(Money total) {
		this.total = total;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Receipt receipt = (Receipt) o;

		if (id != null ? !id.equals(receipt.id) : receipt.id != null) return false;
		if (subtotal != null ? !subtotal.equals(receipt.subtotal) : receipt.subtotal != null) return false;
		if (tax != null ? !tax.equals(receipt.tax) : receipt.tax != null) return false;
		return total != null ? total.equals(receipt.total) : receipt.total == null;

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (subtotal != null ? subtotal.hashCode() : 0);
		result = 31 * result + (tax != null ? tax.hashCode() : 0);
		result = 31 * result + (total != null ? total.hashCode() : 0);
		return result;
	}
}