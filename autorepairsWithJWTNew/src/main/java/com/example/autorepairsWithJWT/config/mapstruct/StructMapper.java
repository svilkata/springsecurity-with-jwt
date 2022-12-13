package com.example.autorepairsWithJWT.config.mapstruct;

import com.example.autorepairsWithJWT.model.dto.sparepart.RimCreatedModifiedResponse;
import com.example.autorepairsWithJWT.model.dto.sparepart.TyreCreatedModifiedResponse;
import com.example.autorepairsWithJWT.model.entity.RimEntity;
import com.example.autorepairsWithJWT.model.entity.TyreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")  //exposed as a Spring bean
public interface StructMapper {

    @Mapping(target = "id", source = "id")
    RimCreatedModifiedResponse rimEntityToRimCreatedModifiedResponseJsonDTO(RimEntity rimEntity);

    @Mapping(target = "id", source = "id")
    TyreCreatedModifiedResponse tyreEntityToTyreCreatedModifiedResponseJsonDTO(TyreEntity tyreEntity);

}

