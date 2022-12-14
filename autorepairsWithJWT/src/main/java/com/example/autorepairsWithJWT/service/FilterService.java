package com.example.autorepairsWithJWT.service;

import com.example.autorepairsWithJWT.config.mapstruct.StructMapper;
import com.example.autorepairsWithJWT.exception.ConflictSparepartException;
import com.example.autorepairsWithJWT.exception.NotFoundSparepart;
import com.example.autorepairsWithJWT.init.InitializableService;
import com.example.autorepairsWithJWT.model.dto.sparepart.FilterRequest;
import com.example.autorepairsWithJWT.model.entity.FilterEntity;
import com.example.autorepairsWithJWT.repository.FilterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilterService implements InitializableService {
    private final FilterRepository filterRepository;
    private final StructMapper filterMapper;

    public FilterService(FilterRepository filterRepository, StructMapper filterMapper) {
        this.filterRepository = filterRepository;
        this.filterMapper = filterMapper;
    }

    @Override
    public void init() {
        if (filterRepository.count() == 0) {
            initFilter("ALFA ROMEO", "33 SPORTWAGON (1984-)", "1.7");
            initFilter("ALFA ROMEO", "GTV (1978-)", "6 2.5");
            initFilter("ALFA ROMEO", "SPIDER (2006-)", "2.0 JTDM");
        }
    }

    private void initFilter(String brand, String model, String modification) {
        FilterEntity filterToAdd = new FilterEntity()
                .setBrand(brand)
                .setModel(model)
                .setModification(modification);

        filterRepository.save(filterToAdd);
    }

    public Optional<FilterEntity> findFilterById(Long filterId) {
        return this.filterRepository.findById(filterId);
    }

    public List<FilterEntity> findAllFilters() {
        return this.filterRepository.findAll();
    }

    public Long addNewFilter(FilterRequest filterRequest) {
        Optional<FilterEntity> filterEntityOpt = this.filterRepository.findByBrandAndModelAndModification(
                filterRequest.getBrand(), filterRequest.getModel(), filterRequest.getModification());

        if (filterEntityOpt.isPresent()) {
            throw new ConflictSparepartException(String.format("Filter brand: %s, model: %s, modification: %s already exists",
                    filterRequest.getBrand(), filterRequest.getModel(), filterRequest.getModification()));
        }

        return this.filterRepository.save(filterMapper.filterRequestToFilterEntity(filterRequest)).getId();
    }

    public void modifyExistingFilter(Long filterId, FilterRequest filterRequest) {
        Optional<FilterEntity> filterEntityOpt = this.filterRepository.findById(filterId);

        filterEntityOpt
                .map(flt -> {
                    if (flt.equalsToRequest(filterRequest)){
                        throw new ConflictSparepartException(String.format("Filter sparepart with id %d not modified - no changes detected", filterId));
                    }
                    return filterRepository.save(filterMapper.filterRequestToFilterEntity(filterRequest).setId(filterId));
                })
                .orElseThrow(() -> new NotFoundSparepart("Filter with %d is not found and can not be modified".formatted(filterId)));
    }

    public void deleteFilter(Long filterId) {
        try {
            filterRepository.deleteById(filterId);
        } catch (Exception ex) {
            throw new NotFoundSparepart("Filter with %d is not found and can not be deleted".formatted(filterId));
        }
    }
}
