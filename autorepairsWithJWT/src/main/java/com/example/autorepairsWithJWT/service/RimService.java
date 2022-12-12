package com.example.autorepairsWithJWT.service;

import com.example.autorepairsWithJWT.config.mapstruct.StructMapper;
import com.example.autorepairsWithJWT.exception.NotFoundItemToDeleteException;
import com.example.autorepairsWithJWT.exception.NotFoundItemToUpdateException;
import com.example.autorepairsWithJWT.init.InitializableService;
import com.example.autorepairsWithJWT.model.dto.sparepart.RimCreateModifyRequestJsonDTO;
import com.example.autorepairsWithJWT.model.dto.sparepart.RimCreatedModifiedResponseJsonDTO;
import com.example.autorepairsWithJWT.model.entity.RimEntity;
import com.example.autorepairsWithJWT.repository.RimRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RimService implements InitializableService {
    private final RimRepository rimRepository;
    private final StructMapper structMapper;

    public RimService(RimRepository rimRepository, StructMapper structMapper) {
        this.rimRepository = rimRepository;
        this.structMapper = structMapper;
    }

    @Override
    public void init() {
        if (rimRepository.count() == 0) {
            initRim("aluminium", "15");
            initRim("steel", "14");
            initRim("aluminium", "17");
        }
    }

    private void initRim(String metalKind, String inches) {
        RimEntity rim = new RimEntity().setMetalKind(metalKind).setInches(inches);

        rimRepository.save(rim);
    }

    public Optional<RimEntity> findRimById(Long rimId) {
        return this.rimRepository.findById(rimId);
    }

    public List<RimEntity> findAllRims() {
        return this.rimRepository.findAll();
    }

    public RimCreatedModifiedResponseJsonDTO addNewRim(RimCreateModifyRequestJsonDTO rimCreateModifyRequestJsonDTO) {
        RimEntity newRimToAdd =
                new RimEntity()
                        .setMetalKind(rimCreateModifyRequestJsonDTO.getMetalKind())
                        .setInches(rimCreateModifyRequestJsonDTO.getInches());


        RimEntity savedInDB = rimRepository.save(newRimToAdd);
        RimCreatedModifiedResponseJsonDTO rimCreatedModifiedResponseJsonDTO =
                this.structMapper.rimEntityToRimCreatedModifiedResponseJsonDTO(savedInDB);

        return rimCreatedModifiedResponseJsonDTO;
    }

    public RimCreatedModifiedResponseJsonDTO modifyExistingRim(Long rimId, RimCreateModifyRequestJsonDTO rimCreateModifyRequestJsonDTO) {
        Optional<RimEntity> rimOpt = this.rimRepository.findById(rimId);
        if (rimOpt.isEmpty()) {
            throw new NotFoundItemToUpdateException("You are trying to update a non-existing item");
        }

        RimEntity rimToModify = rimOpt.get()
                .setMetalKind(rimCreateModifyRequestJsonDTO.getMetalKind())
                .setInches(rimCreateModifyRequestJsonDTO.getInches());

        RimEntity savedInDB = rimRepository.save(rimToModify);
        RimCreatedModifiedResponseJsonDTO rimCreatedModifiedResponseJsonDTO =
                this.structMapper.rimEntityToRimCreatedModifiedResponseJsonDTO(savedInDB);

        return rimCreatedModifiedResponseJsonDTO;
    }

    public void deleteRim(Long rimId) {
        try {
            rimRepository.deleteById(rimId);
        } catch (Exception ex){
            throw new NotFoundItemToDeleteException("You are trying to delete a non-existing item");
        }

    }
}
