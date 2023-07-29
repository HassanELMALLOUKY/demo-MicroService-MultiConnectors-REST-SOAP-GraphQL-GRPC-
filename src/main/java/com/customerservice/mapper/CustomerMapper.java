package com.customerservice.mapper;

import com.customerservice.dto.CustomerRequest;
import com.customerservice.entity.Customer;
import com.customerservice.stub.CustomerServiceOuterClass;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest customerRequest){
        Customer customer=new Customer();
        BeanUtils.copyProperties(customerRequest,customer);
        return customer;
    }

    public CustomerServiceOuterClass.Customer fromCustomerToGrpcCustomer(Customer customer){
        CustomerServiceOuterClass.Customer customer1= CustomerServiceOuterClass.Customer.newBuilder().build();
        BeanUtils.copyProperties(customer,customer1);
        return customer1;
    }

    public Customer fromCustomerRequestToCustomer(CustomerServiceOuterClass.CustomerRequest customerRequest){
        Customer customer1=new Customer();
        BeanUtils.copyProperties(customerRequest,customer1);
        return customer1;
    }
}
