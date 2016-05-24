package com.dugancathal.javabanking;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class BankRepository {
	private Map<String, Bank> bankMap = new HashMap<>();

	public Bank create() {
		Bank bank = new Bank(IdAllocator.getId());
		bankMap.put(bank.getId(), bank);
		return bank;
	}

	public Bank find(String id) {
		return bankMap.get(id);
	}

	private static class IdAllocator {
        private static IdAllocator instance = null;

        public static String getId() {
            return getInstance().generateId();
        }

        private static IdAllocator getInstance() {
            if (instance == null) {
                instance = new IdAllocator();
            }

            return instance;
        }

        private IdAllocator() {
            nextId = 1;
        }

        private int nextId;

        private String generateId() {
            return Integer.toString(nextId++);
        }
    }
}