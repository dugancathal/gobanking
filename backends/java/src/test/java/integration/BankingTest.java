package integration;

import com.dugancathal.javabanking.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest
@SpringApplicationConfiguration(classes = BankingTest.TestApp.class)
public class BankingTest {
	private static final double DOUBLE_TOLERANCE = 0.001;
	private RestTemplate restTemplate;

	@Before
	public void setup() {
		restTemplate = new RestTemplate();
	}

	@Test
	public void purchaseWithSufficientFunds() throws IOException {
		String bankId = createPiggyBank();
		depositFunds(bankId, 20.00);
		addProductToCart("TeddyBear");
		String purchaseId = checkout(bankId).getId();
		Receipt receipt = getReceipt(purchaseId);

		assertEquals(9.99, receipt.getSubtotal().getMoney(), DOUBLE_TOLERANCE);
		assertEquals(0.80, receipt.getTax().getMoney(), DOUBLE_TOLERANCE);
		assertEquals(10.79, receipt.getTotal().getMoney(), DOUBLE_TOLERANCE);

		assertEquals(9.21, fundsIn(bankId).getMoney(), DOUBLE_TOLERANCE);
	}

	private Money fundsIn(String bankId) {
		return restTemplate.getForObject(String.format("http://localhost:8080/banks/%s/funds", bankId), Money.class);
	}

	private Receipt getReceipt(String purchaseId) throws IOException {
		return restTemplate.getForObject(String.format("http://localhost:8080/receipts/%s", purchaseId), Receipt.class);
	}

	private Receipt checkout(String bankId) throws IOException {
		Map<String,String> body = new HashMap<>();
		body.put("account_id", bankId);
		return restTemplate.postForObject("http://localhost:8080/checkout", body, Receipt.class);
	}

	private void addProductToCart(String productName) throws IOException {
		String productId = getProductIdByName(productName);

		Map<String,String> body = new HashMap<>();
		body.put("product_id", productId);
		restTemplate.postForObject("http://localhost:8080/cart/items", body, String.class);
	}

	private String getProductIdByName(String productName) throws IOException {
		Product productResponse = restTemplate.getForObject(String.format("http://localhost:8080/products/%s", productName), Product.class);
		return productResponse.getId();
	}

	private void depositFunds(String bankId, double amount) throws IOException {
		Map<String,String> body = new HashMap<>();
		body.put("money", Double.toString(amount));
		body.put("currency", "USD");
		restTemplate.postForObject(String.format("http://localhost:8080/banks/%s/deposit", bankId), body, Bank.class);
	}

	private String createPiggyBank() throws IOException {
		Bank response = restTemplate.postForObject("http://localhost:8080/banks", null, Bank.class);
		return response.getId();
	}

	@SpringBootApplication(scanBasePackageClasses = {BankApp.class, ProductsApp.class, CartApp.class, PurchaseApp.class})
	public static class TestApp {
		public static void main(String[] args) {
			SpringApplication.run(TestApp.class, args);
		}
	}
}
