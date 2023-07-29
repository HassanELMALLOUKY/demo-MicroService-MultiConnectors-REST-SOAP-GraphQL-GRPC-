package com.customerservice.web.rest;

import com.customerservice.entity.Customer;
import com.customerservice.exception.CustomException;
import com.customerservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerRestController {
    private CustomerRepository customerRepository;

    @GetMapping("")
    public List<Customer> customerList(){
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable(name = "id") Long customerId){
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer==null) throw new CustomException("Customer Not Found");
        return customer;

    }

    @PostMapping("")
    public Customer addCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
        return customer;
    }


    @DeleteMapping("/{id}")
    public void deleteCustomerById(Long customerId){
        customerRepository.deleteById(customerId);
    }
}
