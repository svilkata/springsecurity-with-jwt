package com.example.autorepairsWithJWT.service;

import com.example.autorepairsWithJWT.config.mapstruct.StructMapper;
import com.example.autorepairsWithJWT.exception.ConflictSparepartException;
import com.example.autorepairsWithJWT.exception.NotFoundSparepart;
import com.example.autorepairsWithJWT.init.InitializableService;
import com.example.autorepairsWithJWT.model.dto.sparepart.TyreRequest;
import com.example.autorepairsWithJWT.model.dto.sparepart.TyreResponse;
import com.example.autorepairsWithJWT.model.entity.FilterEntity;
import com.example.autorepairsWithJWT.model.entity.TyreEntity;
import com.example.autorepairsWithJWT.model.enums.TyreKindEnum;
import com.example.autorepairsWithJWT.repository.TyreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TyreService implements InitializableService {
    private final TyreRepository tyreRepository;
    private final ModelMapper tyreMapper;

    public TyreService(TyreRepository tyreRepository, ModelMapper tyreMapper) {
        this.tyreRepository = tyreRepository;
        this.tyreMapper = tyreMapper;
    }

    @Override
    public void init() {
        if (tyreRepository.count() == 0) {
            tyreInit(TyreKindEnum.WINTER, "DEBICA", "145", "70", "15", "No");
            tyreInit(TyreKindEnum.WINTER, "HANKOOK", "175", "75", "16", "XL");
            tyreInit(TyreKindEnum.SUMMER, "DUNLOP", "165", "80", "17", "XL");
            tyreInit(TyreKindEnum.ALL_SEASONS, "TIGAR", "185", "65", "14", "No");
        }
    }

    private void tyreInit(TyreKindEnum tyreKind, String brand, String width, String height,
                          String inches, String isFlat) {
        TyreEntity tyreEntity = new TyreEntity()
                .setTyreKind(tyreKind)
                .setBrand(brand)
                .setWidth(width)
                .setHeight(height)
                .setInches(inches)
                .setFlat(isFlat);

        tyreRepository.save(tyreEntity);
    }

    public Optional<TyreEntity> findTyreById(Long tyreId) {
        return tyreRepository.findById(tyreId);
    }

    public List<TyreEntity> findAllTyres() {
        return tyreRepository.findAll();
    }

    public Long addNewTyre(TyreRequest tyreRequest) {
        Optional<TyreEntity> tyreEntityOpt = tyreRepository.findByTyreKindAndBrandAndWidthAndHeightAndInchesAndFlat(
                TyreKindEnum.valueOf(tyreRequest.getTyreKind()), tyreRequest.getBrand(), tyreRequest.getWidth(), tyreRequest.getHeight(),
                tyreRequest.getInches(), tyreRequest.getFlat());

        if (tyreEntityOpt.isPresent()) {
            throw new ConflictSparepartException(String.format("Tyre kind: %s, brand: %s, width: %s, height: %s, inches: %s, flat: %s not saved - it already exists",
                    tyreRequest.getTyreKind(), tyreRequest.getBrand(), tyreRequest.getWidth(), tyreRequest.getHeight(),
                    tyreRequest.getInches(), tyreRequest.getFlat()
                  ));
        }

        return tyreRepository.save(tyreMapper.map(tyreRequest, TyreEntity.class)).getId();
    }

    public void modifyExistingTyre(Long tyreId, TyreRequest tyreRequest) {
        Optional<TyreEntity> tyreEntityOpt = tyreRepository.findById(tyreId);

        tyreEntityOpt
                .map(tyre -> {
                    if (tyre.equalsToRequest(tyreRequest)){
                        throw new ConflictSparepartException(String.format("Tyre sparepart with id %d not modified - no changes detected", tyreId));
                    }
                    return tyreRepository.save(tyreMapper.map(tyreRequest, TyreEntity.class).setId(tyreId));
                })
                .orElseThrow(() -> new NotFoundSparepart("Tyre with %d is not found and can not be modified".formatted(tyreId)));
    }

    public void deleteTyreById(Long tyreId) {
        try {
            tyreRepository.deleteById(tyreId);
        } catch (RuntimeException ex){
            throw new NotFoundSparepart("Tyre with %d is not found and can not be deleted".formatted(tyreId));
        }
    }
}
