package com.Sintad_test.entity.interfaces;

import com.Sintad_test.documentType.models.entities.TbDocumentType;
import com.Sintad_test.entity.models.entities.TbEntity;
import com.Sintad_test.entity.models.request.EntityDetailsRequest;
import com.Sintad_test.entity.models.response.EntityDetailsResponse;
import com.Sintad_test.taxpayerType.models.entities.TbTaxpayerType;

public interface EntityMapperMethods {
    TbEntity dtoToEntity(EntityDetailsRequest request, TbDocumentType documentType, TbTaxpayerType taxpayerType);
    EntityDetailsResponse entityToDto(TbEntity entity);
}
