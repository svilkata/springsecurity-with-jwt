package com.example.autorepairsWithJWT.web;

import com.example.autorepairsWithJWT.model.dto.sparepart.FilterCreateModifyRequest;
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

    public FilterController(FilterService filterService) {
        this.filterService = filterService;
    }

    //calling GET on http://localhost:8000/spareparts/filters/2
    @GetMapping("/spareparts/filters/{filterId}")
    public ResponseEntity<FilterEntity> getOneFilter(@PathVariable Long filterId) {
        Optional<FilterEntity> filterEntityOpt = this.filterService.findFilterById(filterId);
        return filterEntityOpt.isEmpty()
                ? ResponseEntity.notFound().build()
                :ResponseEntity.ok(filterEntityOpt.get());
    }


    //calling GET on http://localhost:8000/spareparts/filters/all
    @GetMapping("/spareparts/filters/all")
    public ResponseEntity<List<FilterEntity>> getAllFilters() {
        List<FilterEntity> allFilterEntitities = this.filterService.findAllFilters();
        return ResponseEntity.ok(allFilterEntitities);
    }


    //calling POST on http://localhost:8000/spareparts/filters
//    {
//        "brand":"AUDI",
//        "model":"R8 (2007-)",
//        "modification":"4.2 FSI quattro"
//    }
    @PostMapping("/spareparts/filters")
    public ResponseEntity<RimEntity> createRim(
            @RequestBody FilterCreateModifyRequest filterCreateModifyRequest,   //десериализация на body-то до Java обект – пропъртитата на боди-то на нашата заявка ще бъдат популирани върху нашето DTO
            UriComponentsBuilder builder) {

        Long rimId = filterService.addNewFilter(filterCreateModifyRequest);
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
            @RequestBody FilterCreateModifyRequest filterCreateModifyRequest,   //десериализация на body-то до Java обект – пропъртитата на боди-то на нашата заявка ще бъдат популирани върху нашето DTO
            UriComponentsBuilder builder) {

        Long isFilterModified = filterService.modifyExistingFilter(filterId, filterCreateModifyRequest);

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
