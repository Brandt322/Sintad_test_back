package com.Sintad_test.documentType.controller;

import com.Sintad_test.documentType.interfaces.DocumentTypeList;
import com.Sintad_test.documentType.models.request.DocumentTypeRequest;
import com.Sintad_test.documentType.models.response.DocumentTypeResponse;
import com.Sintad_test.exceptions.BadRequestException;
import com.Sintad_test.exceptions.NotFoundException;
import com.Sintad_test.shared.interfaces.Crud;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/document-type")
@RestController
public class DocumentTypeController {
    private final Crud<DocumentTypeRequest, DocumentTypeResponse> crudInterface;
    private final DocumentTypeList documentTypeList;

    public DocumentTypeController(Crud<DocumentTypeRequest, DocumentTypeResponse> crudInterface, DocumentTypeList documentTypeList) {
        this.crudInterface = crudInterface;
        this.documentTypeList = documentTypeList;
    }

    @GetMapping("/documents")
    public ResponseEntity<Object> getAllDocuments() {
        return new ResponseEntity<>(crudInterface.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDocumentTypeById(@PathVariable Long id) {
        try {
            DocumentTypeResponse documentTypeResponse = crudInterface.findById(id);
            return new ResponseEntity<>(documentTypeResponse,HttpStatus.OK);
        } catch (NotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    @PostMapping("/create")
    public ResponseEntity<Object> createDocument(@Valid @RequestBody DocumentTypeRequest documentTypeRequest) {
        if (documentTypeRequest == null) {
            return new ResponseEntity<>("Request cannot be null", HttpStatus.BAD_REQUEST);
        }

        try {
            DocumentTypeResponse documentTypeResponse = crudInterface.create(documentTypeRequest);
            return new ResponseEntity<>(documentTypeResponse, HttpStatus.CREATED);
        } catch (NotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateDocument(@PathVariable Long id,@Valid @RequestBody DocumentTypeRequest documentTypeRequest) {
        if (documentTypeRequest == null) {
            return new ResponseEntity<>("Request cannot be null", HttpStatus.BAD_REQUEST);
        }

        try {
            DocumentTypeResponse documentTypeResponse = crudInterface.update(id, documentTypeRequest);
            return new ResponseEntity<>(documentTypeResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BadRequestException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteDocument(@PathVariable Long id) {
        try {
            crudInterface.delete(id);
            return new ResponseEntity<>("Documento eliminado correctamente", HttpStatus.OK);
        } catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getDocumentTypeList() {
        return new ResponseEntity<>(documentTypeList.findBasicDocumentType(), HttpStatus.OK);
    }
}
