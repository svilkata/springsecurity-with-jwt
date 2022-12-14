package com.example.autorepairsWithJWT.config.mapstruct;

import com.example.autorepairsWithJWT.model.dto.sparepart.*;
import com.example.autorepairsWithJWT.model.dto.userregister.UserDtoResponse;
import com.example.autorepairsWithJWT.model.entity.FilterEntity;
import com.example.autorepairsWithJWT.model.entity.RimEntity;
import com.example.autorepairsWithJWT.model.entity.TyreEntity;
import com.example.autorepairsWithJWT.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)  //exposed as a Spring bean
public abstract class StructMapper {

    @Mapping(target = "fullname", expression="java(mapFirstNameLastNameToFullname(userEntity))")
    public abstract UserDtoResponse userEntityToUserDtoResponse(UserEntity userEntity);

    public String mapFirstNameLastNameToFullname(UserEntity userEntity) {
        return userEntity.getFirstName() + " " + userEntity.getLastName();
    }

    public abstract FilterResponse filterEntityToFilterResponse(FilterEntity filterEntity);

    public abstract FilterEntity filterRequestToFilterEntity(FilterRequest filterRequest);

    public abstract RimResponse rimEntityToRimResponse(RimEntity flt);

    public abstract RimEntity rimRequestToRimEntity(RimRequest rimRequest);
}

