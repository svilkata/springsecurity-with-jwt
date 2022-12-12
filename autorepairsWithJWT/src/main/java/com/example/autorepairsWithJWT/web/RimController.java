package com.example.autorepairsWithJWT.web;

import com.example.autorepairsWithJWT.model.dto.sparepart.RimCreateModifyRequestJsonDTO;
import com.example.autorepairsWithJWT.model.dto.sparepart.RimCreatedModifiedResponseJsonDTO;
import com.example.autorepairsWithJWT.model.entity.RimEntity;
import com.example.autorepairsWithJWT.service.RimService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class RimController {
    private final RimService rimService;

    public RimController(RimService rimService) {
        this.rimService = rimService;
    }

    //calling GET on http://localhost:8000/spareparts/rims/2
    @GetMapping("/spareparts/rims/{rimId}")
    public ResponseEntity<RimEntity> getOneRim(@PathVariable Long rimId) {
        Optional<RimEntity> rimEntityOpt = this.rimService.findRimById(rimId);
        return rimEntityOpt.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(rimEntityOpt.get());
    }

    //calling GET on http://localhost:8000/spareparts/rims/all
    @GetMapping("/spareparts/rims/all")
    public ResponseEntity<List<RimEntity>> getAllRims() {
        List<RimEntity> allRimEntities = this.rimService.findAllRims();
        return ResponseEntity.ok(allRimEntities);
    }


    //calling POST on http://localhost:8000/spareparts/rims
    //    {
    //        "metalKind": "bronze",
    //        "inches": "15"
    //    }
    @PostMapping("/spareparts/rims")
    public ResponseEntity<RimCreatedModifiedResponseJsonDTO> createRim(
            @RequestBody RimCreateModifyRequestJsonDTO rimCreateModifyRequestJsonDTO,
            UriComponentsBuilder builder) {

        RimCreatedModifiedResponseJsonDTO rimCreatedModifiedResponseJsonDTO = rimService.addNewRim(rimCreateModifyRequestJsonDTO);
        URI location = builder.path("/spareparts/rims/{id}")
                .buildAndExpand(rimCreatedModifiedResponseJsonDTO.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(rimCreatedModifiedResponseJsonDTO);
    }

    //calling PUT on http://localhost:8000/spareparts/edit/rims/3
    //    {
    //        "metalKind": "aluminium",
    //        "inches": "20"
    //    }
    @PutMapping("/spareparts/edit/rims/{rimId}")
    public ResponseEntity<RimCreatedModifiedResponseJsonDTO> ModifyRim(
            @PathVariable("rimId") Long rimId,
            @RequestBody RimCreateModifyRequestJsonDTO rimCreateModifyRequestJsonDTO,
            UriComponentsBuilder builder) {

        RimCreatedModifiedResponseJsonDTO rimCreatedModifiedResponseJsonDTO =
                rimService.modifyExistingRim(rimId, rimCreateModifyRequestJsonDTO);

        URI location = builder.path("/spareparts/rims/{id}")
                .buildAndExpand(rimId)
                .toUri();

        return ResponseEntity
                .created(location)
                .body(rimCreatedModifiedResponseJsonDTO);
    }

    //calling DELETE on http://localhost:8000/spareparts/rims/4
    @DeleteMapping("/spareparts/rims/{rimId}")
    public ResponseEntity<RimEntity> deleteRimById(@PathVariable("rimId") Long rimId) {
        this.rimService
                .deleteRim(rimId);

        return ResponseEntity.noContent().build();
    }
}
