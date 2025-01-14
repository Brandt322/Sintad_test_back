package com.Sintad_test.documentType.models.mapper;

import com.Sintad_test.documentType.interfaces.DocumentType;
import com.Sintad_test.documentType.models.entities.TbDocumentType;
import com.Sintad_test.documentType.models.request.DocumentTypeRequest;
import com.Sintad_test.documentType.models.response.DocumentTypeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DocumentTypeMapper implements DocumentType {
    private final ModelMapper modelMapper;

    public DocumentTypeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public DocumentTypeResponse entityToDto(TbDocumentType entity) {
        return modelMapper.map(entity, DocumentTypeResponse.class);
    }

    @Override
    public TbDocumentType dtoToEntity(DocumentTypeRequest request) {
        TbDocumentType tbDocumentType = modelMapper.map(request, TbDocumentType.class);
        tbDocumentType.setState(true);
        return tbDocumentType;
    }
}
