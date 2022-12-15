package com.example.autorepairsWithJWT.init;

import com.example.autorepairsWithJWT.model.dto.sparepart.RimRequest;
import com.example.autorepairsWithJWT.model.dto.sparepart.RimResponse;
import com.example.autorepairsWithJWT.model.dto.userauth.UserLoginAuthResponse;
import com.example.autorepairsWithJWT.model.entity.RimEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

        Thread.sleep(500);
        createOneRimViaRestWebClientAndAuthorizedUser();
    }

    private void getAllRims() {
        ResponseEntity<RimResponse[]> allRimsResponse = restWebClient
                .getForEntity("http://localhost:8000/spareparts/rims/all", RimResponse[].class);

        if (allRimsResponse.hasBody()) {
            for (RimResponse rim : allRimsResponse.getBody()) {
                System.out.println("Rim: " + rim);
            }
        }
    }

    private void getOneRim() {
        ResponseEntity<RimResponse> oneRimResponse = restWebClient
                .getForEntity("http://localhost:8000/spareparts/rims/1", RimResponse.class);

        if (oneRimResponse.hasBody()) {
            System.out.println("Rim: " + oneRimResponse.getBody());
        }
    }

    private void createOneRimViaRestWebClientAndAuthorizedUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, String> mvpBody = new LinkedMultiValueMap<>();
//        {
//            "username": "Svilen",
//            "password": "topsecrect"
//        }
        mvpBody.add("username", "Svilen");
        mvpBody.add("password", "topsecret");

        HttpEntity<MultiValueMap<String, String>> requesUserLogin = new HttpEntity<>(mvpBody, headers);

        // TODO: no body
        ResponseEntity<UserLoginAuthResponse> userLoginAuthResponse = restWebClient
                .postForEntity("http://localhost:8000/users/login", requesUserLogin, UserLoginAuthResponse.class);



        headers.setBearerAuth(userLoginAuthResponse.getBody().getAccessToken());

        MultiValueMap<String, String> mvpRimRequest = new LinkedMultiValueMap<>();
        //    {
        //        "metalKind": "bronze",
        //        "inches": "15"
        //    }
        mvpRimRequest.add("metalKind", "bronze");
        mvpRimRequest.add("inches", "15");

//        RimRequest newRimJsonToAdd =
//                new RimRequest()
//                        .setMetalKind("bronze")
//                        .setInches("15");
//
//        RimEntity newRimEntity = new RimEntity()
//                .setMetalKind(newRimJsonToAdd.getMetalKind())
//                .setInches(newRimJsonToAdd.getInches());
//
//        HttpEntity<RimEntity> request = new HttpEntity<>(newRimEntity, headers);

        restWebClient
                .postForEntity("http://localhost:8000/spareparts/rims", mvpRimRequest, RimResponse.class);
    }

}
