package ecommsapp.billingservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"app.seed-data.enabled=false", "eureka.client.enabled=false"})
class BillingServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
