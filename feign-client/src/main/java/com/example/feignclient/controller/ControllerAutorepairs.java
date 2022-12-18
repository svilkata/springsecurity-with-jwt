package com.example.feignclient.controller;

import com.example.feignclient.model.RimResponse;
import com.example.feignclient.service.ServiceAutorepairs;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerAutorepairs {
    private final ServiceAutorepairs serviceAutorepairs;

    public ControllerAutorepairs(ServiceAutorepairs serviceAutorepairs) {
        this.serviceAutorepairs = serviceAutorepairs;
    }

    @GetMapping(path = "/feign/rims/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<RimResponse> getAllRims(){
        return serviceAutorepairs.getAllRims();
    }

    @GetMapping(path = "/feign/rims/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public RimResponse getRimById(@PathVariable("id") String id){
        return serviceAutorepairs.getRimById(id);
    }
}
