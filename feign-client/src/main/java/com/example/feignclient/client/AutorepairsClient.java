package com.example.feignclient.client;

import com.example.feignclient.model.RimResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@FeignClient(name = "${auto-repairs-service-client.name}", url = "${auto-repairs-service-client.domain-url}")
public interface AutorepairsClient {

    @GetMapping(path = "${auto-repairs-service-client.retrieve-all-rims.url}")
    List<RimResponse> getAllRims();

    @GetMapping(path = "${auto-repairs-service-client.retrieve-rim-by-id.url}")
    RimResponse getRimById(@PathVariable("id") String id);
}
