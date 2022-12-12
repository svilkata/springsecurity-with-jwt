package com.example.autorepairsWithJWT.config.mapstruct;

import com.example.autorepairsWithJWT.model.dto.sparepart.RimCreatedModifiedResponseJsonDTO;
import com.example.autorepairsWithJWT.model.dto.sparepart.TyreCreatedModifiedResponseJsonDTO;
import com.example.autorepairsWithJWT.model.entity.RimEntity;
import com.example.autorepairsWithJWT.model.entity.TyreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")  //exposed as a Spring bean
public interface StructMapper {

    @Mapping(target = "id")
    RimCreatedModifiedResponseJsonDTO rimEntityToRimCreatedModifiedResponseJsonDTO(RimEntity rimEntity);

    @Mapping(target = "id")
    TyreCreatedModifiedResponseJsonDTO tyreEntityToTyreCreatedModifiedResponseJsonDTO(TyreEntity tyreEntity);

}

