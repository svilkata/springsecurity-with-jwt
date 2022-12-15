package com.example.autorepairsWithJWT.web;

import com.example.autorepairsWithJWT.exception.NotFoundSparepart;
import com.example.autorepairsWithJWT.model.dto.sparepart.TyreRequest;
import com.example.autorepairsWithJWT.model.dto.sparepart.TyreResponse;
import com.example.autorepairsWithJWT.model.entity.TyreEntity;
import com.example.autorepairsWithJWT.service.TyreService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class TyreController {
    private final TyreService tyreService;
    private final ModelMapper tyreMapper;

    public TyreController(TyreService tyreService, ModelMapper tyreMapper) {
        this.tyreService = tyreService;
        this.tyreMapper = tyreMapper;
    }

    //calling GET on http://localhost:8000/spareparts/tyres/2
    @GetMapping("/spareparts/tyres/{tyreId}")
    public ResponseEntity<TyreResponse> getOneTyre(@PathVariable Long tyreId) {

        Optional<TyreEntity> tyreEntityOpt = tyreService.findTyreById(tyreId);

        return tyreEntityOpt
                .map(tyre -> ResponseEntity.ok(tyreMapper.map(tyre, TyreResponse.class)))
                .orElseThrow(() -> new NotFoundSparepart("Tyre with id " + tyreId + " not found"));
    }

    //calling GET on http://localhost:8000/spareparts/tyres/all
    @GetMapping("/spareparts/tyres/all")
    public ResponseEntity<List<TyreResponse>> getAllTyres() {

        return ResponseEntity.ok(tyreService.findAllTyres().stream()
                .map(tyre -> tyreMapper.map(tyre, TyreResponse.class))
                .toList());
    }

    //calling POST on http://localhost:8000/spareparts/tyres
//    {
//        "tyreKind": "ALL_SEASONS",
//        "brand": "TIGAR BEST",
//        "width": "180",
//        "height": "70",
//        "inches": "15",
//        "flat": "XL"
//    }
    @PostMapping("/spareparts/tyres")
    public ResponseEntity<?> createTyre(@RequestBody TyreRequest tyreRequest, UriComponentsBuilder builder) {

        Long tyreId = tyreService.addNewTyre(tyreRequest);
        URI location = builder.path("/spareparts/tyres/{id}")
                .buildAndExpand(tyreId)
                .toUri();

        return ResponseEntity.created(location).build();
    }


    //calling PUT on http://localhost:8000/spareparts/edit/tyres/2
//    {
//        "tyreKind": "SUMMER",
//        "brand": "HANKOOK",
//        "width": "175",
//        "height": "75",
//        "inches": "16",
//        "flat": "XL"
//    }
    @PutMapping("/spareparts/edit/tyres/{tyreId}")
    public ResponseEntity<?> ModifyTyre(
            @PathVariable("tyreId") Long tyreId, @RequestBody TyreRequest tyreRequest, UriComponentsBuilder builder) {

        tyreService.modifyExistingTyre(tyreId, tyreRequest);

        URI location = builder.path("/spareparts/tyres/{id}")
                .buildAndExpand(tyreId)
                .toUri();

        return ResponseEntity.ok().location(location).build();
    }

    //calling DELETE on http://localhost:8000/spareparts/tyres/4
    @DeleteMapping("/spareparts/tyres/{tyreId}")
    public ResponseEntity<?> deleteTyreById(@PathVariable Long tyreId) {

        tyreService.deleteTyreById(tyreId);

        return ResponseEntity.noContent().build();
    }
}
