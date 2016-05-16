package com.dugancathal.javabanking;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
	private final String id;
	private final String name;
	private final String description;
	private final Money price;

	@JsonCreator
	public Product(@JsonProperty("id") String id,
				   @JsonProperty("name") String name,
				   @JsonProperty("description") String description,
				   @JsonProperty("price") Money price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Money getPrice() {
		return price;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Product product = (Product) o;

		if (id != null ? !id.equals(product.id) : product.id != null) return false;
		if (name != null ? !name.equals(product.name) : product.name != null) return false;
		if (description != null ? !description.equals(product.description) : product.description != null) return false;
		return price != null ? price.equals(product.price) : product.price == null;

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (price != null ? price.hashCode() : 0);
		return result;
	}
}