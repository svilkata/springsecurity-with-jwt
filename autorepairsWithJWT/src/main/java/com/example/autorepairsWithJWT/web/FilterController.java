package com.example.autorepairsWithJWT.web;

import com.example.autorepairsWithJWT.config.mapstruct.StructMapper;
import com.example.autorepairsWithJWT.exception.NotFoundSparepart;
import com.example.autorepairsWithJWT.model.dto.sparepart.FilterRequest;
import com.example.autorepairsWithJWT.model.dto.sparepart.FilterResponse;
import com.example.autorepairsWithJWT.model.entity.FilterEntity;
import com.example.autorepairsWithJWT.model.entity.RimEntity;
import com.example.autorepairsWithJWT.service.FilterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class FilterController {
    private final FilterService filterService;
    private final StructMapper filterMapper;

    public FilterController(FilterService filterService, StructMapper filterMapper) {
        this.filterService = filterService;
        this.filterMapper = filterMapper;
    }

    //calling GET on http://localhost:8000/spareparts/filters/2
    @GetMapping("/spareparts/filters/{filterId}")
    public ResponseEntity<FilterResponse> getOneFilter(@PathVariable Long filterId) {
        Optional<FilterEntity> filterEntityOpt = this.filterService.findFilterById(filterId);

        return filterEntityOpt
                .map(flt -> ResponseEntity.ok(filterMapper.filterEntityToFilterResponse(flt)))
                .orElseThrow(() -> new NotFoundSparepart("Filter with id " + filterId + " not found"));
    }


    //calling GET on http://localhost:8000/spareparts/filters/all
    @GetMapping("/spareparts/filters/all")
    public ResponseEntity<List<FilterResponse>> getAllFilters() {
        return ResponseEntity.ok(this.filterService.findAllFilters().stream()
                .map(flt -> filterMapper.filterEntityToFilterResponse(flt))
                .toList());
    }


    //calling POST on http://localhost:8000/spareparts/filters
//    {
//        "brand":"AUDI",
//        "model":"R8 (2007-)",
//        "modification":"4.2 FSI quattro"
//    }
    @PostMapping("/spareparts/filters")
    public ResponseEntity<RimEntity> createFilter(@RequestBody FilterRequest filterRequest, UriComponentsBuilder builder) {

        Long rimId = filterService.addNewFilter(filterRequest);
        URI location = builder.path("/spareparts/filters/{id}")
                .buildAndExpand(rimId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    //calling PUT on http://localhost:8000/spareparts/edit/filters/4
//    {
//        "brand":"AUDI",
//        "model":"R8 (2007-)",
//        "modification":"5.2 FSI quattro"
//    }
    @PutMapping("/spareparts/edit/filters/{filterId}")
    public ResponseEntity<FilterEntity> ModifyFilter(
            @PathVariable("filterId") Long filterId,
            @RequestBody FilterRequest filterRequest,
            UriComponentsBuilder builder) {

        Long isFilterModified = filterService.modifyExistingFilter(filterId, filterRequest);

        URI location = builder.path("/spareparts/filters/{id}")
                .buildAndExpand(filterId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    //calling DELETE on http://localhost:8000/spareparts/filters/4
    @DeleteMapping("/spareparts/filters/{filterId}")
    public ResponseEntity<FilterEntity> deleteFIlterById(@PathVariable Long filterId) {
        this.filterService
                .deleteFilter(filterId);

        return ResponseEntity.noContent().build();
    }
}
