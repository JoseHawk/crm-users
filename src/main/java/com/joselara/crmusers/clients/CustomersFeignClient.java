package com.joselara.crmusers.clients;

import com.joselara.crmusers.models.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("crm-customers")
public interface CustomersFeignClient {

    @GetMapping(path = "/customers", consumes = "application/json")
    ResponseEntity<List<CustomerDTO>> getAllCustomers();
}
