package com.example.autorepairsWithJWT.service;

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

    public FilterService(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
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

    //TODO: finish exception handling
    public Long addNewFilter(FilterRequest filterRequest) {
        Optional<FilterEntity> filterEntityExists = this.filterRepository.findByBrandAndModelAndModification();

        //TODO: mapstruct here to use
        filterEntityExists
                .map(flt -> new FilterEntity()
                        .setBrand(filterRequest.getBrand())
                        .setModel(filterRequest.getModel())
                        .setModification(filterRequest.getModification()))
                .orElseThrow(() -> new CONFLICT exception)

        FilterEntity newFilterToAdd =


        return this.filterRepository.save(newFilterToAdd).getId();
    }

    public Long modifyExistingFilter(Long filterId, FilterRequest filterRequest) {
        Optional<FilterEntity> filterOpt = this.filterRepository.findById(filterId);
        if (filterOpt.isEmpty()) {
            throw new NotFoundSparepart("You are trying to update a non-existing item");
        }

        FilterEntity filterToModify = filterOpt.get()
                .setBrand(filterRequest.getBrand())
                .setModel(filterRequest.getModel())
                .setModification(filterRequest.getModification());

        FilterEntity savedInDB = filterRepository.save(filterToModify);

        return savedInDB.getId();
    }

    public void deleteFilter(Long filterId) {
        try {
            filterRepository.deleteById(filterId);
        } catch (Exception ex) {
            throw new NotFoundSparepart("You are trying to delete a non-existing sparepart");
        }

    }
}
