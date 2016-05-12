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
}