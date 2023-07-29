package com.customerservice.web.graphql;

import com.customerservice.dto.CustomerRequest;
import com.customerservice.entity.Customer;
import com.customerservice.mapper.CustomerMapper;
import com.customerservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class CustomerGraphqlController {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @QueryMapping
    public List<Customer> allCustomers(){
        return customerRepository.findAll();
    }

    @QueryMapping
    public Customer getCustomerById(@Argument(name = "id") Long customerId){
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer==null) throw new RuntimeException(String.format("Customer Not Found"));
        return customer;
    }
    @MutationMapping
    public Customer saveCustomer(@Argument(name = "customer") CustomerRequest customerRequest){
        Customer customer = customerMapper.toCustomer(customerRequest);
        return customerRepository.save(customer);
    }
}
