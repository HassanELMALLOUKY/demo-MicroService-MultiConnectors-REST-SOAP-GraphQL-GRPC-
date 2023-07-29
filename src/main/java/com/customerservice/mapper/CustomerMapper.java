package com.customerservice.mapper;

import com.customerservice.dto.CustomerRequest;
import com.customerservice.entity.Customer;
import com.customerservice.stub.CustomerServiceOuterClass;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class CustomerMapper {
    private ModelMapper modelMapper=new ModelMapper();

    public Customer toCustomer(CustomerRequest customerRequest){
        return modelMapper.map(customerRequest,Customer.class);
    }

    public CustomerServiceOuterClass.Customer fromCustomerToGrpcCustomer(Customer customer){
        return modelMapper.map(customer, CustomerServiceOuterClass.Customer.Builder.class).build();
    }

    public Customer fromCustomerRequestToCustomer(CustomerServiceOuterClass.CustomerRequest customerRequest){
        return modelMapper.map(customerRequest,Customer.class);
    }
}
