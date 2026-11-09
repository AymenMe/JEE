package ecommsapp.billingservice;

import ecommsapp.billingservice.entities.Bill;
import ecommsapp.billingservice.entities.ProductItem;
import ecommsapp.billingservice.model.Product;
import ecommsapp.billingservice.repositories.BillRepository;
import ecommsapp.billingservice.repositories.ProductItemRepository;
import ecommsapp.billingservice.service.CustomerRestClient;
import ecommsapp.billingservice.service.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    @ConditionalOnProperty(name = "app.seed-data.enabled", havingValue = "true", matchIfMissing = true)
    CommandLineRunner commandLineRunner(BillRepository billRepository,
                                        ProductItemRepository productItemRepository,
                                        CustomerRestClient customerRestClient,
                                        ProductRestClient productRestClient) {
        return args -> {
            Collection<Product> products = productRestClient.allProducts().getContent();
            Long customerId = 1L;
            if (customerRestClient.findCustomerById(customerId) == null) {
                throw new RuntimeException("Customer not found");
            }
            Bill bill = billRepository.save(Bill.builder()
                    .billDate(new Date())
                    .customerId(customerId)
                    .build());
            products.forEach(product -> productItemRepository.save(ProductItem.builder()
                    .bill(bill)
                    .productId(product.getId())
                    .quantity(new Random().nextInt(10) + 1)
                    .price(product.getPrice())
                    .discount(Math.random())
                    .build()));
        };
    }
}
