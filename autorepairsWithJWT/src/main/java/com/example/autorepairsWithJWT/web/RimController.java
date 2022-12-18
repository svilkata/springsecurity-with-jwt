package com.example.autorepairsWithJWT.web;

import com.example.autorepairsWithJWT.config.mapstruct.StructMapper;
import com.example.autorepairsWithJWT.exception.NotFoundSparepart;
import com.example.autorepairsWithJWT.model.dto.sparepart.RimRequest;
import com.example.autorepairsWithJWT.model.dto.sparepart.RimResponse;
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
    private final StructMapper rimMapper;

    public RimController(RimService rimService, StructMapper rimMapper) {
        this.rimService = rimService;
        this.rimMapper = rimMapper;
    }

    //calling GET on http://localhost:8000/spareparts/rims/2
    @GetMapping("/spareparts/rims/{rimId}")
    public ResponseEntity<RimResponse> getOneRim(@PathVariable Long rimId) {

        Optional<RimEntity> rimEntityOpt = rimService.findRimById(rimId);

        return rimEntityOpt
                .map(flt -> ResponseEntity.ok(rimMapper.rimEntityToRimResponse(flt)))
                .orElseThrow(() -> new NotFoundSparepart("Rim with id " + rimId + " not found"));
    }

    //calling GET on http://localhost:8000/spareparts/rims/all
    @GetMapping("/spareparts/rims/all")
    public ResponseEntity<List<RimResponse>> getAllRims() {

        return ResponseEntity.ok(rimService.findAllRims().stream()
                .map(flt -> rimMapper.rimEntityToRimResponse(flt))
                .toList());
    }


    //calling POST on http://localhost:8000/spareparts/rims
    //    {
    //        "metalKind": "bronze",
    //        "inches": "15"
    //    }
    @PostMapping("/spareparts/rims")
    public ResponseEntity<?> createRim(@RequestBody RimRequest rimRequest, UriComponentsBuilder builder) {

        Long rimId = rimService.addNewRim(rimRequest);
        URI location = builder.path("/spareparts/rims/{id}")
                .buildAndExpand(rimId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    //calling PUT on http://localhost:8000/spareparts/edit/rims/3
    //    {
    //        "metalKind": "aluminium",
    //        "inches": "20"
    //    }
    @PutMapping("/spareparts/edit/rims/{rimId}")
    public ResponseEntity<?> ModifyRim(
            @PathVariable("rimId") Long rimId, @RequestBody RimRequest rimRequest, UriComponentsBuilder builder) {

        rimService.modifyExistingRim(rimId, rimRequest);

        URI location = builder.path("/spareparts/rims/{id}")
                .buildAndExpand(rimId)
                .toUri();

        return ResponseEntity.ok().location(location).build();
    }

    //calling DELETE on http://localhost:8000/spareparts/rims/4
    @DeleteMapping("/spareparts/rims/{rimId}")
    public ResponseEntity<?> deleteRimById(@PathVariable("rimId") Long rimId) {

        rimService.deleteRim(rimId);

        return ResponseEntity.noContent().build();
    }
}
