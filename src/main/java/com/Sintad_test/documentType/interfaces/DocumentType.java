package com.Sintad_test.documentType.interfaces;

import com.Sintad_test.documentType.models.entities.TbDocumentType;
import com.Sintad_test.documentType.models.request.DocumentTypeRequest;
import com.Sintad_test.documentType.models.response.DocumentTypeBasicResponse;
import com.Sintad_test.documentType.models.response.DocumentTypeResponse;

public interface DocumentType {
    TbDocumentType dtoToEntity(DocumentTypeRequest request);
    DocumentTypeResponse entityToDto(TbDocumentType entity);
    DocumentTypeBasicResponse entityToDtoBasic(TbDocumentType entity);
}
