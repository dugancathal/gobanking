package com.dugancathal.javabanking;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Receipt {
	private final String id;
	private Money subtotal;
	private Money tax;
	private Money total;
	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@JsonCreator
	public Receipt(@JsonProperty("id") String id,
				   @JsonProperty("subtotal") Money subtotal,
				   @JsonProperty("tax") Money tax,
				   @JsonProperty("total") Money total,
				   @JsonProperty("products") List<Product> products) {
		this.id = id;
		this.subtotal = subtotal;
		this.tax = tax;
		this.total = total;
		this.products = products;
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
		return Objects.equals(id, receipt.id) &&
				Objects.equals(subtotal, receipt.subtotal) &&
				Objects.equals(tax, receipt.tax) &&
				Objects.equals(total, receipt.total) &&
				Objects.equals(products, receipt.products);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, subtotal, tax, total, products);
	}
}