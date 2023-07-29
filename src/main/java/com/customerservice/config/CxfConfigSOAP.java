package com.customerservice.config;

import com.customerservice.web.soap.CustomerSoapWebService;
import jakarta.websocket.EndpointConfig;
import lombok.AllArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@AllArgsConstructor
public class CxfConfigSOAP {
    private Bus bus;
    private CustomerSoapWebService customerSoapWebService;

    @Bean
    public EndpointImpl endpoint(){
        EndpointImpl endpoint=new EndpointImpl(bus,customerSoapWebService);
        endpoint.publish("CustomerWS");
        return endpoint;
    }
}
