package com.dugancathal.javabanking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PurchaseController {
	private static final double TAX_RATE = 0.08;
	private ReceiptRepository receiptRepository;
	private CartService cartService;
	private ProductService productService;
	private BankService bankService;

	@Autowired
	public PurchaseController(ReceiptRepository receiptRepository, CartService cartService, ProductService productService, BankService bankService) {
		this.receiptRepository = receiptRepository;
		this.cartService = cartService;
		this.productService = productService;
		this.bankService = bankService;
	}

	@RequestMapping(path="/checkout", method= RequestMethod.POST)
	public Receipt checkout(@RequestBody Map<String,String> body) {
		List<Product> products = cartService.getItemIds().stream()
				.map(productId -> productService.getProduct(productId)).collect(Collectors.toList());

		Optional<Money> maybeSubtotal = products.stream()
				.map(product -> product.getPrice())
				.reduce((memo, price) -> memo.add(price));

		if (maybeSubtotal.isPresent()) {
			Money subtotal = maybeSubtotal.get();
			int taxAmount = (int)Math.ceil(subtotal.getPennies() * TAX_RATE);

			Money tax = new Money(taxAmount);
			Money total = subtotal.add(tax);

			bankService.makeWithdrawal(body.get("account_id"), total);

			return receiptRepository.create(subtotal, tax, total, products);
		} else {
			return new Receipt("1", new Money(0), new Money(0), new Money(0), Collections.emptyList());
		}
	}

	@RequestMapping(path="/receipts/{id}", method=RequestMethod.GET)
	public Receipt getReceipt(@PathVariable String id) {
		return receiptRepository.find(id);
	}
}