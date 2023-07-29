package com.customerservice.web.soap;

import com.customerservice.dto.CustomerRequest;
import com.customerservice.entity.Customer;
import com.customerservice.mapper.CustomerMapper;
import com.customerservice.repository.CustomerRepository;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@WebService
public class CustomerSoapWebService {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @WebMethod
    public List<Customer> customerList(){
        return customerRepository.findAll();
    }
    @WebMethod
    public Customer getCustomerById(@WebParam Long id){
        return customerRepository.findById(id).get();
    }
    @WebMethod
    public Customer saveCustomer(@WebParam CustomerRequest customerRequest){
        return customerRepository.save(customerMapper.toCustomer(customerRequest));
    }

    @WebMethod
    public void deleteCustomerById(@WebParam Long id){
        customerRepository.deleteById(id);
    }
}
