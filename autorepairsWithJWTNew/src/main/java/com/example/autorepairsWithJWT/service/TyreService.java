package com.example.autorepairsWithJWT.service;

import com.example.autorepairsWithJWT.config.mapstruct.StructMapper;
import com.example.autorepairsWithJWT.exception.NotFoundItemToDeleteException;
import com.example.autorepairsWithJWT.exception.NotFoundItemToUpdateException;
import com.example.autorepairsWithJWT.init.InitializableService;
import com.example.autorepairsWithJWT.model.dto.sparepart.TyreCreateModifyRequest;
import com.example.autorepairsWithJWT.model.dto.sparepart.TyreCreatedModifiedResponse;
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
    private final ModelMapper modelMapper;
    private final StructMapper structMapper;

    public TyreService(TyreRepository tyreRepository, ModelMapper modelMapper, StructMapper structMapper) {
        this.tyreRepository = tyreRepository;
        this.modelMapper = modelMapper;
        this.structMapper = structMapper;
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

        this.tyreRepository.save(tyreEntity);
    }

    public Optional<TyreEntity> findTyreById(Long tyreId) {
        return this.tyreRepository.findById(tyreId);
    }

    public List<TyreEntity> findAllTyres() {
        return this.tyreRepository.findAll();
    }

    //TODO: make it here only with modelMapper???
    public TyreCreatedModifiedResponse addNewTyre(TyreCreateModifyRequest tyreCreateModifyRequest) {
        TyreEntity newTyreToAdd = this.modelMapper.map(tyreCreateModifyRequest, TyreEntity.class);

        TyreEntity savedInDB = tyreRepository.save(newTyreToAdd);
        TyreCreatedModifiedResponse tyreCreatedModifiedResponse =
                this.structMapper.tyreEntityToTyreCreatedModifiedResponseJsonDTO(savedInDB);

        return tyreCreatedModifiedResponse;
    }

    public TyreCreatedModifiedResponse modifyExistingTyre(Long tyreId, TyreCreateModifyRequest tyreCreateModifyRequest) {
        Optional<TyreEntity> tyreOpt = this.tyreRepository.findById(tyreId);
        if (tyreOpt.isEmpty()) {
            throw new NotFoundItemToUpdateException("You are trying to update a non-existing item");
        }

        TyreEntity tyreToModify = this.modelMapper.map(tyreCreateModifyRequest, TyreEntity.class);
        tyreToModify.setId(tyreId);

        TyreEntity savedInDB = tyreRepository.save(tyreToModify);
        TyreCreatedModifiedResponse tyreCreatedModifiedResponse =
                this.structMapper.tyreEntityToTyreCreatedModifiedResponseJsonDTO(savedInDB);

        return tyreCreatedModifiedResponse;
    }

    public void deleteTyreById(Long tyreId) {
        try {
            this.tyreRepository.deleteById(tyreId);
        } catch (Exception ex){
            throw new NotFoundItemToDeleteException("You are trying to delete a non-existing item");
        }
    }
}
