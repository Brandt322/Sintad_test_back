package com.Sintad_test.documentType.service;

import com.Sintad_test.documentType.models.entities.TbDocumentType;
import com.Sintad_test.documentType.models.mapper.DocumentTypeMapper;
import com.Sintad_test.documentType.models.request.DocumentTypeRequest;
import com.Sintad_test.documentType.models.response.DocumentTypeResponse;
import com.Sintad_test.documentType.repository.DocumentTypeRepository;
import com.Sintad_test.exceptions.BadRequestException;
import com.Sintad_test.exceptions.NotFoundException;
import com.Sintad_test.shared.interfaces.Crud;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentTypeService implements Crud<DocumentTypeRequest, DocumentTypeResponse> {

    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentTypeMapper mapEntityToDto;

    public DocumentTypeService(DocumentTypeRepository documentTypeRepository, DocumentTypeMapper mapEntityToDto) {
        this.documentTypeRepository = documentTypeRepository;
        this.mapEntityToDto = mapEntityToDto;
    }

    @Override
    public List<DocumentTypeResponse> findAll() {
        return documentTypeRepository.findAlltoList();
    }

    @Override
    public DocumentTypeResponse findById(Long id) {
        Optional<DocumentTypeResponse> documentTypeResponse = documentTypeRepository.findDocumentTypeById(id);
        if (documentTypeResponse.isEmpty()) {
            throw new NotFoundException("Document type not found with id: " + id);
        }
        return documentTypeResponse.get();
    }

    @Override
    public DocumentTypeResponse create(DocumentTypeRequest request) {
        if(request.equals(null)){
            throw new NotFoundException("Data is empty");
        }

        TbDocumentType tbDocumentType = mapEntityToDto.dtoToEntity(request);
        documentTypeRepository.save(tbDocumentType);

        return mapEntityToDto.entityToDto(tbDocumentType);
    }

    @Override
    public DocumentTypeResponse update(Long id, DocumentTypeRequest request) {
        TbDocumentType tbDocumentType = documentTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("Document type not found with id: " + id));

        tbDocumentType.setCode(request.getCode());
        tbDocumentType.setName(request.getName());
        tbDocumentType.setDescription(request.getDescription());

        documentTypeRepository.save(tbDocumentType);
        return mapEntityToDto.entityToDto(tbDocumentType);
    }

    @Override
    public void delete(Long id) {
        Optional<DocumentTypeResponse> documentType = documentTypeRepository.findDocumentTypeById(id);

        if(id == null || id == 0){
            throw new BadRequestException("Id is empty");
        }

        if (documentType.isEmpty()) {
            throw new NotFoundException("Document type not found with id: " + id);
        }

        documentTypeRepository.deleteById(id);
    }
}
