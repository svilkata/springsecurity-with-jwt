package com.example.autorepairsWithJWT.config.mapstruct;

import com.example.autorepairsWithJWT.model.dto.sparepart.FilterRequest;
import com.example.autorepairsWithJWT.model.dto.sparepart.FilterResponse;
import com.example.autorepairsWithJWT.model.dto.sparepart.RimRequest;
import com.example.autorepairsWithJWT.model.dto.sparepart.RimResponse;
import com.example.autorepairsWithJWT.model.dto.userregister.UserDtoResponse;
import com.example.autorepairsWithJWT.model.dto.userregister.UserRoleObject;
import com.example.autorepairsWithJWT.model.entity.FilterEntity;
import com.example.autorepairsWithJWT.model.entity.RimEntity;
import com.example.autorepairsWithJWT.model.entity.UserEntity;
import com.example.autorepairsWithJWT.model.entity.UserRoleEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-16T11:54:56+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class StructMapperImpl extends StructMapper {

    @Override
    public UserDtoResponse userEntityToUserDtoResponse(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDtoResponse userDtoResponse = new UserDtoResponse();

        userDtoResponse.setId( userEntity.getId() );
        userDtoResponse.setEmail( userEntity.getEmail() );
        userDtoResponse.setUsername( userEntity.getUsername() );
        userDtoResponse.setUserRoles( userRoleEntityListToUserRoleObjectList( userEntity.getUserRoles() ) );

        userDtoResponse.setFullname( mapFirstNameLastNameToFullname(userEntity) );

        return userDtoResponse;
    }

    @Override
    public FilterResponse filterEntityToFilterResponse(FilterEntity filterEntity) {
        if ( filterEntity == null ) {
            return null;
        }

        FilterResponse filterResponse = new FilterResponse();

        filterResponse.setId( filterEntity.getId() );
        filterResponse.setBrand( filterEntity.getBrand() );
        filterResponse.setModel( filterEntity.getModel() );
        filterResponse.setModification( filterEntity.getModification() );

        return filterResponse;
    }

    @Override
    public FilterEntity filterRequestToFilterEntity(FilterRequest filterRequest) {
        if ( filterRequest == null ) {
            return null;
        }

        FilterEntity filterEntity = new FilterEntity();

        filterEntity.setBrand( filterRequest.getBrand() );
        filterEntity.setModel( filterRequest.getModel() );
        filterEntity.setModification( filterRequest.getModification() );

        return filterEntity;
    }

    @Override
    public RimResponse rimEntityToRimResponse(RimEntity flt) {
        if ( flt == null ) {
            return null;
        }

        RimResponse rimResponse = new RimResponse();

        rimResponse.setId( flt.getId() );
        rimResponse.setMetalKind( flt.getMetalKind() );
        rimResponse.setInches( flt.getInches() );

        return rimResponse;
    }

    @Override
    public RimEntity rimRequestToRimEntity(RimRequest rimRequest) {
        if ( rimRequest == null ) {
            return null;
        }

        RimEntity rimEntity = new RimEntity();

        rimEntity.setMetalKind( rimRequest.getMetalKind() );
        rimEntity.setInches( rimRequest.getInches() );

        return rimEntity;
    }

    protected UserRoleObject userRoleEntityToUserRoleObject(UserRoleEntity userRoleEntity) {
        if ( userRoleEntity == null ) {
            return null;
        }

        UserRoleObject userRoleObject = new UserRoleObject();

        if ( userRoleEntity.getUserRole() != null ) {
            userRoleObject.setUserRole( userRoleEntity.getUserRole().name() );
        }

        return userRoleObject;
    }

    protected List<UserRoleObject> userRoleEntityListToUserRoleObjectList(List<UserRoleEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<UserRoleObject> list1 = new ArrayList<UserRoleObject>( list.size() );
        for ( UserRoleEntity userRoleEntity : list ) {
            list1.add( userRoleEntityToUserRoleObject( userRoleEntity ) );
        }

        return list1;
    }
}
