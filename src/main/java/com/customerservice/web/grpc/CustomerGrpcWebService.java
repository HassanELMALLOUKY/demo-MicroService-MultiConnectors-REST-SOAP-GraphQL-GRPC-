package com.customerservice.web.grpc;

import com.customerservice.entity.Customer;
import com.customerservice.mapper.CustomerMapper;
import com.customerservice.repository.CustomerRepository;
import com.customerservice.stub.CustomerServiceGrpc;
import com.customerservice.stub.CustomerServiceOuterClass;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@GrpcService
@AllArgsConstructor
public class CustomerGrpcWebService extends CustomerServiceGrpc.CustomerServiceImplBase {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Override
    public void getAllCustomers(CustomerServiceOuterClass.GetAllCustomersRequest request, StreamObserver<CustomerServiceOuterClass.GetAllCustomersResponse> responseObserver) {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerServiceOuterClass.Customer> grpcCustomers = customers.stream().map(customerMapper::fromCustomerToGrpcCustomer).collect(Collectors.toList());
        CustomerServiceOuterClass.GetAllCustomersResponse customersResponse= CustomerServiceOuterClass.GetAllCustomersResponse
                .newBuilder()
                .addAllCustomers(grpcCustomers)
                .build();
        responseObserver.onNext(customersResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getCustomerById(CustomerServiceOuterClass.GetCustomerByIdRequest request, StreamObserver<CustomerServiceOuterClass.GetCustomerByIdResponse> responseObserver) {
        Customer customer = customerRepository.findById(request.getCustomerId()).get();
        CustomerServiceOuterClass.Customer grpcCustomer = customerMapper.fromCustomerToGrpcCustomer(customer);
        CustomerServiceOuterClass.GetCustomerByIdResponse customerByIdResponse= CustomerServiceOuterClass.GetCustomerByIdResponse
                .newBuilder()
                .setCustomer(grpcCustomer)
                .build();
        responseObserver.onNext(customerByIdResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void saveCustomer(CustomerServiceOuterClass.saveCustomerRequest request, StreamObserver<CustomerServiceOuterClass.saveCustomerResponse> responseObserver) {

        Customer customer = customerMapper.fromCustomerRequestToCustomer(request.getCustomerRequest());
        Customer savedCustomer = customerRepository.save(customer);
        CustomerServiceOuterClass.saveCustomerResponse saveCustomerResponse= CustomerServiceOuterClass.saveCustomerResponse
                .newBuilder()
                .setCustomer(customerMapper.fromCustomerToGrpcCustomer(savedCustomer))
                .build();
        responseObserver.onNext(saveCustomerResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteCustomer(CustomerServiceOuterClass.deleteCustomerRequest request, StreamObserver<CustomerServiceOuterClass.deleteCustomerResponse> responseObserver) {
        customerRepository.deleteById(request.getId());
        super.deleteCustomer(request, responseObserver);
    }
}
