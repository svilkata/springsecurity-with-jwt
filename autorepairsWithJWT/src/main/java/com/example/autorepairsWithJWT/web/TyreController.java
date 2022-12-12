package com.example.autorepairsWithJWT.web;

import com.example.autorepairsWithJWT.model.dto.sparepart.TyreCreateModifyRequestJsonDTO;
import com.example.autorepairsWithJWT.model.dto.sparepart.TyreCreatedModifiedResponseJsonDTO;
import com.example.autorepairsWithJWT.model.entity.TyreEntity;
import com.example.autorepairsWithJWT.service.TyreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class TyreController {
    private final TyreService tyreService;

    public TyreController(TyreService tyreService) {
        this.tyreService = tyreService;
    }

    //calling GET on http://localhost:8000/spareparts/tyres/2
    @GetMapping("/spareparts/tyres/{tyreId}")
    public ResponseEntity<TyreEntity> getOneRim(@PathVariable Long tyreId) {
        Optional<TyreEntity> tyreEntityOpt = this.tyreService.findTyreById(tyreId);
        return tyreEntityOpt.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(tyreEntityOpt.get());
    }

    //calling GET on http://localhost:8000/spareparts/tyres/all
    @GetMapping("/spareparts/tyres/all")
    public ResponseEntity<List<TyreEntity>> getAllRims() {
        List<TyreEntity> allTyreEntitities = this.tyreService.findAllTyres();
        return ResponseEntity.ok(allTyreEntitities);
    }

    //calling POST on http://localhost:8000/spareparts/tyres
//    {
//        "tyreKind": "ALL_SEASONS",
//            "brand": "TIGAR BEST",
//            "width": "180",
//            "height": "70",
//            "inches": "15",
//            "flat": "XL"
//    }
    @PostMapping("/spareparts/tyres")
    public ResponseEntity<TyreCreatedModifiedResponseJsonDTO> createTyre(
            @RequestBody TyreCreateModifyRequestJsonDTO tyreCreateModifyRequestJsonDTO,   //десериализация на body-то до Java обект – пропъртитата на боди-то на нашата заявка ще бъдат популирани върху нашето DTO
            UriComponentsBuilder builder) {

        TyreCreatedModifiedResponseJsonDTO tyreCreatedModifiedResponseJsonDTO = tyreService.addNewTyre(tyreCreateModifyRequestJsonDTO);
        URI location = builder.path("/spareparts/tyres/{id}")
                .buildAndExpand(tyreCreatedModifiedResponseJsonDTO.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(tyreCreatedModifiedResponseJsonDTO);
    }


    //calling PUT on http://localhost:8000/spareparts/edit/tyres/2
//    {
//        "tyreKind": "SUMMER",
//            "brand": "HANKOOK",
//            "width": "175",
//            "height": "75",
//            "inches": "16",
//            "flat": "XL"
//    }
    @PutMapping("/spareparts/edit/tyres/{tyreId}")
    public ResponseEntity<TyreCreatedModifiedResponseJsonDTO> ModifyTyre(
            @PathVariable("tyreId") Long tyreId,
            @RequestBody TyreCreateModifyRequestJsonDTO tyreCreateModifyRequestJsonDTO,
            UriComponentsBuilder builder) {

        TyreCreatedModifiedResponseJsonDTO tyreCreatedModifiedResponseJsonDTO = tyreService.modifyExistingTyre(tyreId, tyreCreateModifyRequestJsonDTO);

        URI location = builder.path("/spareparts/tyres/{id}")
                .buildAndExpand(tyreId)
                .toUri();

        return ResponseEntity
                .created(location)
                .body(tyreCreatedModifiedResponseJsonDTO);
    }

    //calling DELETE on http://localhost:8000/spareparts/tyres/4
    @DeleteMapping("/spareparts/tyres/{tyreId}")
    public ResponseEntity<TyreEntity> deleteTyreById(@PathVariable Long tyreId) {
        this.tyreService
                .deleteTyreById(tyreId);

        return ResponseEntity.noContent().build();
    }
}
