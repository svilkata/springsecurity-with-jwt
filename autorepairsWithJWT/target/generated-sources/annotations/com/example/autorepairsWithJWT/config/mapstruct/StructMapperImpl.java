package com.example.autorepairsWithJWT.config.mapstruct;

import com.example.autorepairsWithJWT.model.dto.sparepart.RimCreatedModifiedResponseJsonDTO;
import com.example.autorepairsWithJWT.model.dto.sparepart.TyreCreatedModifiedResponseJsonDTO;
import com.example.autorepairsWithJWT.model.entity.RimEntity;
import com.example.autorepairsWithJWT.model.entity.TyreEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-11T19:43:52+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class StructMapperImpl implements StructMapper {

    @Override
    public RimCreatedModifiedResponseJsonDTO rimEntityToRimCreatedModifiedResponseJsonDTO(RimEntity rimEntity) {
        if ( rimEntity == null ) {
            return null;
        }

        RimCreatedModifiedResponseJsonDTO rimCreatedModifiedResponseJsonDTO = new RimCreatedModifiedResponseJsonDTO();

        rimCreatedModifiedResponseJsonDTO.setId( rimEntity.getId() );
        rimCreatedModifiedResponseJsonDTO.setMetalKind( rimEntity.getMetalKind() );
        rimCreatedModifiedResponseJsonDTO.setInches( rimEntity.getInches() );

        return rimCreatedModifiedResponseJsonDTO;
    }

    @Override
    public TyreCreatedModifiedResponseJsonDTO tyreEntityToTyreCreatedModifiedResponseJsonDTO(TyreEntity tyreEntity) {
        if ( tyreEntity == null ) {
            return null;
        }

        TyreCreatedModifiedResponseJsonDTO tyreCreatedModifiedResponseJsonDTO = new TyreCreatedModifiedResponseJsonDTO();

        tyreCreatedModifiedResponseJsonDTO.setId( tyreEntity.getId() );
        if ( tyreEntity.getTyreKind() != null ) {
            tyreCreatedModifiedResponseJsonDTO.setTyreKind( tyreEntity.getTyreKind().name() );
        }
        tyreCreatedModifiedResponseJsonDTO.setBrand( tyreEntity.getBrand() );
        tyreCreatedModifiedResponseJsonDTO.setWidth( tyreEntity.getWidth() );
        tyreCreatedModifiedResponseJsonDTO.setHeight( tyreEntity.getHeight() );
        tyreCreatedModifiedResponseJsonDTO.setInches( tyreEntity.getInches() );
        tyreCreatedModifiedResponseJsonDTO.setFlat( tyreEntity.getFlat() );

        return tyreCreatedModifiedResponseJsonDTO;
    }
}
