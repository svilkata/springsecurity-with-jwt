package com.example.feignclient.service;

import com.example.feignclient.client.AutorepairsClient;
import com.example.feignclient.model.RimResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAutorepairs {
    private final AutorepairsClient autorepairsClient;

    public ServiceAutorepairs(AutorepairsClient autorepairsClient) {
        this.autorepairsClient = autorepairsClient;
    }

    public List<RimResponse> getAllRims(){
        return autorepairsClient.getAllRims();
    }

    public RimResponse getRimById(String id){
        return autorepairsClient.getRimById(id);
    }
}
