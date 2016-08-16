package integration;

import com.dugancathal.javabanking.BankApp;
import com.dugancathal.javabanking.CartApp;
import com.dugancathal.javabanking.ProductsApp;
import com.dugancathal.javabanking.PurchaseApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {BankApp.class, ProductsApp.class, CartApp.class, PurchaseApp.class})
public class BankApi {
    public static void main(String[] args) {
        SpringApplication.run(BankApi.class, args);
    }
}
