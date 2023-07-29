package com.customerservice;

import com.customerservice.entity.Customer;
import com.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceMultiConnectorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceMultiConnectorsApplication.class, args);
    }


    @Bean
    CommandLineRunner start(CustomerRepository customerRepository){
        return args -> {
            customerRepository.save(Customer.builder().name("Hassan").email("hassan@gmail.com").build());
            customerRepository.save(Customer.builder().name("Mourad").email("mourad@gmail.com").build());
            customerRepository.save(Customer.builder().name("Saida").email("saida@gmail.com").build());
        };
    }
}
