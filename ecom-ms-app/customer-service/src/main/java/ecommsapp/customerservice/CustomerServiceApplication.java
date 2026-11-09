package ecommsapp.customerservice;

import ecommsapp.customerservice.config.CustomerConfigParams;
import ecommsapp.customerservice.entities.Customer;
import ecommsapp.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.saveAll(List.of(
                    Customer.builder().name("Hassan").email("hassan@gmail.com").build(),
                    Customer.builder().name("Mouad").email("mouad@gmail.com").build(),
                    Customer.builder().name("Amine").email("amine@gmail.com").build()
            ));
        };
    }

}
