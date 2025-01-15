package com.Sintad_test.documentType.service;

import com.Sintad_test.documentType.interfaces.DocumentType;
import com.Sintad_test.documentType.models.entities.TbDocumentType;
import com.Sintad_test.documentType.models.request.DocumentTypeRequest;
import com.Sintad_test.documentType.models.response.DocumentTypeResponse;
import com.Sintad_test.documentType.repository.DocumentTypeRepository;
import com.Sintad_test.exceptions.BadRequestException;
import com.Sintad_test.exceptions.NotFoundException;
import com.Sintad_test.shared.interfaces.Crud;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DocumentTypeService implements Crud<DocumentTypeRequest, DocumentTypeResponse> {

    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentType documentType;

    public DocumentTypeService(DocumentTypeRepository documentTypeRepository, DocumentType mapEntityToDto, Validator validator) {
        this.documentTypeRepository = documentTypeRepository;
        this.documentType = mapEntityToDto;
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

        TbDocumentType tbDocumentType = documentType.dtoToEntity(request);
        documentTypeRepository.save(tbDocumentType);

        return documentType.entityToDto(tbDocumentType);
    }

    @Override
    public DocumentTypeResponse update(Long id, DocumentTypeRequest request) {
        TbDocumentType tbDocumentType = documentTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("Document type not found with id: " + id));

        tbDocumentType.setCode(request.getCode());
        tbDocumentType.setName(request.getName());
        tbDocumentType.setDescription(request.getDescription());
        tbDocumentType.setState(request.getState());

        documentTypeRepository.save(tbDocumentType);
        return documentType.entityToDto(tbDocumentType);
    }

    @Override
    public void delete(Long id) {

        if(id == null || id == 0){
            throw new BadRequestException("Id is empty");
        }

        Optional<DocumentTypeResponse> documentTypeResponse = documentTypeRepository.findDocumentTypeById(id);

        if (documentTypeResponse.isEmpty()) {
            throw new NotFoundException("Document type not found with id: " + id);
        }

        documentTypeRepository.deleteById(id);
    }
}
