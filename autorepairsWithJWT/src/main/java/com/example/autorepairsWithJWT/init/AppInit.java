package com.example.autorepairsWithJWT.init;

import com.example.autorepairsWithJWT.model.dto.sparepart.RimCreateModifyRequestJsonDTO;
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
    private final RestTemplate restTemplate; //нашия Bean

    //Всички service класове, които сме имплементирали с InitializableService interface,
    // тук ни се зареждат автоматично
    private final List<InitializableService> allServices;

    public AppInit(RestTemplate restTemplate, List<InitializableService> allServices) {
        this.restTemplate = restTemplate;
        this.allServices = allServices;
    }

    @PostConstruct
    public void beginInit() {
        this.allServices.forEach(srvc -> srvc.init());
    }

    @Override
    public void run(String... args) throws Exception {
        getAllRims();
        getOneRim();
//        addOneRim();
    }

    private void getAllRims() {
        ResponseEntity<RimCreateModifyRequestJsonDTO[]> allRimsResponse = restTemplate
                .getForEntity("http://localhost:8000/spareparts/rims/all", RimCreateModifyRequestJsonDTO[].class);

        if (allRimsResponse.hasBody()) {
            for (RimCreateModifyRequestJsonDTO rim : allRimsResponse.getBody()) {
                System.out.println("Rim: " + rim);
            }
        }
    }

    private void getOneRim() {
        ResponseEntity<RimCreateModifyRequestJsonDTO> oneRimResponse = restTemplate
                .getForEntity("http://localhost:8000/spareparts/rims/1", RimCreateModifyRequestJsonDTO.class);
        if (oneRimResponse.hasBody()) {
            System.out.println("Rim: " + oneRimResponse.getBody());
        }
    }

    private void addOneRim() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

//        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
//        //    {
//        //        "metalKind": "bronze",
//        //        "inches": "15"
//        //    }
//        map.add("metalKind", "bronze");
//        map.add("inches", "15");

        RimCreateModifyRequestJsonDTO newRimJsonToAdd =
                new RimCreateModifyRequestJsonDTO()
                        .setMetalKind("bronze")
                        .setInches("15");

        RimEntity newRimEntity = new RimEntity()
                .setMetalKind(newRimJsonToAdd.getMetalKind())
                .setInches(newRimJsonToAdd.getInches());

        HttpEntity<RimEntity> request = new HttpEntity<>(newRimEntity, headers);

//caught by the @RestController :)
        ResponseEntity<RimEntity> oneRimPost = restTemplate
                .postForEntity("http://localhost:8000/spareparts/rims", request, RimEntity.class);

        //If we do not have the @RestController with @PostMapping("/spareparts/rims") in class RimController,
        // then we can update the db with the below line
//        rimService.addNewRim(newRimJsonToAdd);
    }

}
