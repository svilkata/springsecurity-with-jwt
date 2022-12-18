package com.example.autorepairsWithJWT.init;

import com.example.autorepairsWithJWT.model.dto.sparepart.RimRequest;
import com.example.autorepairsWithJWT.model.dto.sparepart.RimResponse;
import com.example.autorepairsWithJWT.model.dto.userauth.UserLoginAuthRequest;
import com.example.autorepairsWithJWT.model.dto.userauth.UserLoginAuthResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class AppInit implements CommandLineRunner {
    private final RestTemplate restTemplateWebClient;
    private final List<InitializableService> allServices;

    public AppInit(RestTemplate restTemplateWebClient, List<InitializableService> allServices) {
        this.restTemplateWebClient = restTemplateWebClient;
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

//        createOneRimViaRestWebClientAndAuthorizedUser();
    }

    private void getAllRims() {
        ResponseEntity<RimResponse[]> allRimsResponse = restTemplateWebClient
                .getForEntity("http://localhost:8000/spareparts/rims/all", RimResponse[].class);

        if (allRimsResponse.hasBody()) {
            for (RimResponse rim : allRimsResponse.getBody()) {
                System.out.println("Getting all Rims: " + rim);
            }
        }
    }

    private void getOneRim() {
        ResponseEntity<RimResponse> oneRimResponse = restTemplateWebClient
                .getForEntity("http://localhost:8000/spareparts/rims/1", RimResponse.class);

        if (oneRimResponse.hasBody()) {
            System.out.println("Getting rim with id 1: " + oneRimResponse.getBody());
        }
    }

    private void createOneRimViaRestWebClientAndAuthorizedUser() {
//        ResponseEntity<UserLoginAuthResponse> userLoginAuthResponse = authorizeUser("Svilen", "topsecret");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
//        headers.setBasicAuth("Svilen", "topsecret");

//        headers.setBearerAuth(userLoginAuthResponse.getBody().getAccessToken());

        //Manual entered JWT
        headers.setBearerAuth("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY3MTA5Nzg0OSwiZXhwIjoxNjcxMTg0MjQ5fQ.C0GYJV2JSrlgKLuT5EimXWSeAg_kl7UFK4U7vTc4ErIbfmiVgK8zBoKVbviNBYfUkMx0Pi3BKQd7QUnz9YWidg");

        HttpEntity<RimRequest> request = new HttpEntity<>(new RimRequest().setMetalKind("bronze").setInches("15"), headers);

        ResponseEntity<RimResponse> rimResponse = restTemplateWebClient
                .postForEntity("http://localhost:8000/spareparts/rims", request, RimResponse.class);

        System.out.println("Status code for creating demo Rim is: " + rimResponse.getStatusCode());
    }

    private ResponseEntity<UserLoginAuthResponse> authorizeUser(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Content-Type", "application/json");
        headers.set("Accept", "*/*");
        headers.set("Accept-Encoding", "gzip, deflate, br");
        headers.set("Connection", "keep-alive");
        headers.set("Cache-Control", "no-cache");

        HttpEntity<UserLoginAuthRequest> requestUserLogin = new HttpEntity<>(new UserLoginAuthRequest(username, password), headers);

//        return restTemplateWebClient
//                .postForEntity("http://localhost:8000/users/login", requestUserLogin, UserLoginAuthResponse.class);

        return restTemplateWebClient
                .exchange("http://localhost:8000/users/login", HttpMethod.POST, requestUserLogin, UserLoginAuthResponse.class);
    }
}
