package com.example.autorepairsWithJWT.service;

import com.example.autorepairsWithJWT.exception.NotFoundItemToDeleteException;
import com.example.autorepairsWithJWT.exception.NotFoundItemToUpdateException;
import com.example.autorepairsWithJWT.init.InitializableService;
import com.example.autorepairsWithJWT.model.dto.sparepart.FilterCreateModifyRequest;
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

    public Long addNewFilter(FilterCreateModifyRequest filterCreateModifyRequest) {
        FilterEntity newFilterToAdd =
                new FilterEntity()
                        .setBrand(filterCreateModifyRequest.getBrand())
                        .setModel(filterCreateModifyRequest.getModel())
                        .setModification(filterCreateModifyRequest.getModification());


        FilterEntity savedInDB = filterRepository.save(newFilterToAdd);

        return savedInDB.getId();
    }

    public Long modifyExistingFilter(Long filterId, FilterCreateModifyRequest filterCreateModifyRequest) {
        Optional<FilterEntity> filterOpt = this.filterRepository.findById(filterId);
        if (filterOpt.isEmpty()) {
            throw new NotFoundItemToUpdateException("You are trying to update a non-existing item");
        }

        FilterEntity filterToModify = filterOpt.get()
                .setBrand(filterCreateModifyRequest.getBrand())
                .setModel(filterCreateModifyRequest.getModel())
                .setModification(filterCreateModifyRequest.getModification());

        FilterEntity savedInDB = filterRepository.save(filterToModify);

        return savedInDB.getId();
    }

    public void deleteFilter(Long filterId) {
        try {
            filterRepository.deleteById(filterId);
        } catch (Exception ex) {
            throw new NotFoundItemToDeleteException("You are trying to delete a non-existing item");
        }

    }
}
