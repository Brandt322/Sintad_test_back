package com.Sintad_test.documentType.controller;

import com.Sintad_test.documentType.models.request.DocumentTypeRequest;
import com.Sintad_test.documentType.models.response.DocumentTypeResponse;
import com.Sintad_test.exceptions.BadRequestException;
import com.Sintad_test.exceptions.NotFoundException;
import com.Sintad_test.shared.interfaces.Crud;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/document-type")
@RestController
public class DocumentTypeController {
    private final Crud<DocumentTypeRequest, DocumentTypeResponse> crudInterface;

    public DocumentTypeController(Crud<DocumentTypeRequest, DocumentTypeResponse> crudInterface) {
        this.crudInterface = crudInterface;
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

    @PostMapping("/create")
    public ResponseEntity<Object> createDocument(@Valid @RequestBody DocumentTypeRequest documentTypeRequest) {
        try {
            DocumentTypeResponse documentTypeResponse = crudInterface.create(documentTypeRequest);
            return new ResponseEntity<>(documentTypeResponse, HttpStatus.CREATED);
        } catch (NotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateDocument(@PathVariable Long id,@Valid @RequestBody DocumentTypeRequest documentTypeRequest) {
        try {
            DocumentTypeResponse documentTypeResponse = crudInterface.update(id, documentTypeRequest);
            return new ResponseEntity<>(documentTypeResponse, HttpStatus.OK);
        } catch (NotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BadRequestException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteDocument(@PathVariable Long id) {
        try {
            crudInterface.delete(id);
            return new ResponseEntity<>("Document type deleted successfully", HttpStatus.OK);
        } catch (NotFoundException notFoundException){
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BadRequestException badRequestException){
            return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
