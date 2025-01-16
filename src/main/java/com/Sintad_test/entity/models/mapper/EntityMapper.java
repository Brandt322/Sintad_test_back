package com.Sintad_test.entity.models.mapper;

import com.Sintad_test.documentType.models.entities.TbDocumentType;
import com.Sintad_test.documentType.models.mapper.DocumentTypeMapper;
import com.Sintad_test.entity.interfaces.EntityMapperMethods;
import com.Sintad_test.entity.models.entities.TbEntity;
import com.Sintad_test.entity.models.request.EntityDetailsRequest;
import com.Sintad_test.entity.models.response.EntityDetailsResponse;
import com.Sintad_test.taxpayerType.models.entities.TbTaxpayerType;
import com.Sintad_test.taxpayerType.models.mapper.TaxpayerTypeMapper;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper implements EntityMapperMethods {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DocumentTypeMapper documentTypeMapper;

    @Autowired
    private TaxpayerTypeMapper taxpayerTypeMapper;

    @PostConstruct
    public void init() {
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        // Crear un PropertyMap específico para ServiceEntityDetails -> ServiceDetailsResponse
        modelMapper.addMappings(new PropertyMap<EntityDetailsRequest, TbEntity>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
    }

    @Override
    public TbEntity dtoToEntity(EntityDetailsRequest request, TbDocumentType documentType, TbTaxpayerType taxpayerType) {
        TbEntity tbEntity = modelMapper.map(request, TbEntity.class);
        tbEntity.setTbDocumentType(documentType);
        tbEntity.setTbTaxpayerType(taxpayerType);
        tbEntity.setState(true);
        return tbEntity;
    }

    @Override
    public EntityDetailsResponse entityToDto(TbEntity entity) {
        EntityDetailsResponse entityDetailsResponse = modelMapper.map(entity, EntityDetailsResponse.class);
        entityDetailsResponse.setDocumentTypeBasicResponse(documentTypeMapper.entityToDtoBasic(entity.getTbDocumentType()));

        if (entity.getTbTaxpayerType() != null) {
            entityDetailsResponse.setTaxpayerTypeBasicResponse(taxpayerTypeMapper.entityToDto(entity.getTbTaxpayerType()));
        } else {
            entityDetailsResponse.setTaxpayerTypeBasicResponse(null);
        }
        return entityDetailsResponse;
    }
}
