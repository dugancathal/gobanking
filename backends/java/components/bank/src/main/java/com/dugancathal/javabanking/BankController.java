package com.dugancathal.javabanking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankController {
	private final BankRepository repository;

	@Autowired
	public BankController(BankRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(path = "/banks", method = RequestMethod.POST)
	public Bank createAccount() {
		return repository.create();
	}

	@RequestMapping(path = "/banks/{id}/deposit", method = RequestMethod.POST)
	public Bank depositMoney(@PathVariable String id, @RequestBody Money deposit) {
		Bank bank = repository.find(id);
		bank.deposit(deposit);
		return bank;
	}

	@RequestMapping(path = "banks/{id}/funds", method = RequestMethod.GET)
	public Money getFunds(@PathVariable String id) {
		return repository.find(id).getHoldings();
	}

	@RequestMapping(path = "banks/{id}/withdrawal", method = RequestMethod.POST)
	public void withdrawFunds(@PathVariable String id, @RequestBody Money money) {
		Bank bank = repository.find(id);
		bank.withdraw(money);
	}
}
