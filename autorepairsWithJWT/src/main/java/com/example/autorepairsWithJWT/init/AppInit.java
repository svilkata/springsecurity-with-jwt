package com.example.autorepairsWithJWT.init;

import com.example.autorepairsWithJWT.model.dto.sparepart.RimCreateModifyRequest;
import com.example.autorepairsWithJWT.model.entity.RimEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class AppInit implements CommandLineRunner {
    private final RestTemplate restWebClient;
    private final List<InitializableService> allServices;

    public AppInit(RestTemplate restWebClient, List<InitializableService> allServices) {
        this.restWebClient = restWebClient;
        this.allServices = allServices;
    }

    @PostConstruct
    public void beginInit() {
        //OPEN-CLOSE principle implemented here
        this.allServices.forEach(srvc -> srvc.init());
    }

    @Override
    public void run(String... args) throws Exception {
        getAllRims();
        getOneRim();
//        creteOneRimViaRestWebClientAuthorizedUser();
    }

    private void getAllRims() {
        ResponseEntity<RimCreateModifyRequest[]> allRimsResponse = restWebClient
                .getForEntity("http://localhost:8000/spareparts/rims/all", RimCreateModifyRequest[].class);

        if (allRimsResponse.hasBody()) {
            for (RimCreateModifyRequest rim : allRimsResponse.getBody()) {
                System.out.println("Rim: " + rim);
            }
        }
    }

    private void getOneRim() {
        ResponseEntity<RimCreateModifyRequest> oneRimResponse = restWebClient
                .getForEntity("http://localhost:8000/spareparts/rims/1", RimCreateModifyRequest.class);
        if (oneRimResponse.hasBody()) {
            System.out.println("Rim: " + oneRimResponse.getBody());
        }
    }

    //TODO:
    private void creteOneRimViaRestWebClientAuthorizedUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

//        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
//        //    {
//        //        "metalKind": "bronze",
//        //        "inches": "15"
//        //    }
//        map.add("metalKind", "bronze");
//        map.add("inches", "15");

        RimCreateModifyRequest newRimJsonToAdd =
                new RimCreateModifyRequest()
                        .setMetalKind("bronze")
                        .setInches("15");

        RimEntity newRimEntity = new RimEntity()
                .setMetalKind(newRimJsonToAdd.getMetalKind())
                .setInches(newRimJsonToAdd.getInches());

        HttpEntity<RimEntity> request = new HttpEntity<>(newRimEntity, headers);

        ResponseEntity<RimEntity> oneRimPost = restWebClient
                .postForEntity("http://localhost:8000/spareparts/rims", request, RimEntity.class);
    }

}
