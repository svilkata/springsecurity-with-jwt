package com.example.autorepairsWithJWT.config.mapstruct;

import com.example.autorepairsWithJWT.model.dto.sparepart.RimCreatedModifiedResponse;
import com.example.autorepairsWithJWT.model.dto.sparepart.TyreCreatedModifiedResponse;
import com.example.autorepairsWithJWT.model.dto.userregister.UserDtoResponse;
import com.example.autorepairsWithJWT.model.dto.userregister.UserRoleObject;
import com.example.autorepairsWithJWT.model.entity.RimEntity;
import com.example.autorepairsWithJWT.model.entity.TyreEntity;
import com.example.autorepairsWithJWT.model.entity.UserEntity;
import com.example.autorepairsWithJWT.model.entity.UserRoleEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-14T00:26:39+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
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
    public RimCreatedModifiedResponse rimEntityToRimCreatedModifiedResponseJsonDTO(RimEntity rimEntity) {
        if ( rimEntity == null ) {
            return null;
        }

        RimCreatedModifiedResponse rimCreatedModifiedResponse = new RimCreatedModifiedResponse();

        rimCreatedModifiedResponse.setId( rimEntity.getId() );
        rimCreatedModifiedResponse.setMetalKind( rimEntity.getMetalKind() );
        rimCreatedModifiedResponse.setInches( rimEntity.getInches() );

        return rimCreatedModifiedResponse;
    }

    @Override
    public TyreCreatedModifiedResponse tyreEntityToTyreCreatedModifiedResponseJsonDTO(TyreEntity tyreEntity) {
        if ( tyreEntity == null ) {
            return null;
        }

        TyreCreatedModifiedResponse tyreCreatedModifiedResponse = new TyreCreatedModifiedResponse();

        tyreCreatedModifiedResponse.setId( tyreEntity.getId() );
        if ( tyreEntity.getTyreKind() != null ) {
            tyreCreatedModifiedResponse.setTyreKind( tyreEntity.getTyreKind().name() );
        }
        tyreCreatedModifiedResponse.setBrand( tyreEntity.getBrand() );
        tyreCreatedModifiedResponse.setWidth( tyreEntity.getWidth() );
        tyreCreatedModifiedResponse.setHeight( tyreEntity.getHeight() );
        tyreCreatedModifiedResponse.setInches( tyreEntity.getInches() );
        tyreCreatedModifiedResponse.setFlat( tyreEntity.getFlat() );

        return tyreCreatedModifiedResponse;
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
