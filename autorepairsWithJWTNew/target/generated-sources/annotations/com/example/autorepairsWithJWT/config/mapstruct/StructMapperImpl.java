package com.example.autorepairsWithJWT.config.mapstruct;

import com.example.autorepairsWithJWT.model.dto.sparepart.RimCreatedModifiedResponse;
import com.example.autorepairsWithJWT.model.dto.sparepart.TyreCreatedModifiedResponse;
import com.example.autorepairsWithJWT.model.entity.RimEntity;
import com.example.autorepairsWithJWT.model.entity.TyreEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-13T17:01:53+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class StructMapperImpl implements StructMapper {

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
}
