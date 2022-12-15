package com.example.autorepairsWithJWT.service;

import com.example.autorepairsWithJWT.config.mapstruct.StructMapper;
import com.example.autorepairsWithJWT.exception.ConflictSparepartException;
import com.example.autorepairsWithJWT.exception.NotFoundSparepart;
import com.example.autorepairsWithJWT.init.InitializableService;
import com.example.autorepairsWithJWT.model.dto.sparepart.RimRequest;
import com.example.autorepairsWithJWT.model.dto.sparepart.RimResponse;
import com.example.autorepairsWithJWT.model.entity.FilterEntity;
import com.example.autorepairsWithJWT.model.entity.RimEntity;
import com.example.autorepairsWithJWT.repository.RimRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RimService implements InitializableService {
    private final RimRepository rimRepository;
    private final StructMapper rimMapper;

    public RimService(RimRepository rimRepository, StructMapper rimMapper) {
        this.rimRepository = rimRepository;
        this.rimMapper = rimMapper;
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
        return rimRepository.findAll();
    }

    public Long addNewRim(RimRequest rimRequest) {
        Optional<RimEntity> rimEntityOpt = rimRepository.findByMetalKindAndInches(
                rimRequest.getMetalKind(), rimRequest.getInches());

        if (rimEntityOpt.isPresent()) {
            throw new ConflictSparepartException(String.format("Rim metal kind: %s, inches: %s not saved - it already exists",
                    rimRequest.getMetalKind(), rimRequest.getInches()));
        }

        return rimRepository.save(rimMapper.rimRequestToRimEntity(rimRequest)).getId();
    }

    public void modifyExistingRim(Long rimId, RimRequest rimRequest) {
        Optional<RimEntity> rimEntityOpt = rimRepository.findById(rimId);

        rimEntityOpt
                .map(rm -> {
                    if (rm.equalsToRequest(rimRequest)){
                        throw new ConflictSparepartException(String.format("Rim sparepart with id %d not modified - no changes detected", rimId));
                    }
                    return rimRepository.save(rimMapper.rimRequestToRimEntity(rimRequest).setId(rimId));
                })
                .orElseThrow(() -> new NotFoundSparepart("Rim with %d is not found and can not be modified".formatted(rimId)));
    }

    public void deleteRim(Long rimId) {
        try {
            rimRepository.deleteById(rimId);
        } catch (RuntimeException ex) {
            throw new NotFoundSparepart("Rim with %d is not found and can not be deleted".formatted(rimId));
        }
    }
}
