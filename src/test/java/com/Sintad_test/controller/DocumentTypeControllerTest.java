package com.Sintad_test.controller;

import com.Sintad_test.documentType.controller.DocumentTypeController;
import com.Sintad_test.documentType.interfaces.DocumentTypeList;
import com.Sintad_test.documentType.models.request.DocumentTypeRequest;
import com.Sintad_test.documentType.models.response.DocumentTypeResponse;
import com.Sintad_test.exceptions.NotFoundException;
import com.Sintad_test.shared.interfaces.Crud;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class DocumentTypeControllerTest {
    @Mock
    private Crud<DocumentTypeRequest, DocumentTypeResponse> crudInterface;

    @Mock
    private DocumentTypeList documentTypeList;

    @InjectMocks
    private DocumentTypeController documentTypeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMINISTRADOR")
    public void testCreateDocument_NullRequest() {
        // Call the endpoint with a null request
        ResponseEntity<Object> response = documentTypeController.createDocument(null);

        // Assert that the response status is BAD_REQUEST
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetDocumentTypeById_InvalidId() {
        // Simulate the behavior when an invalid ID is provided
        when(crudInterface.findById(anyLong())).thenThrow(new NotFoundException("Not Found"));

        // Call the endpoint with an invalid ID
        ResponseEntity<Object> response = documentTypeController.getDocumentTypeById(-1L);

        // Assert that the response status is NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Not Found", response.getBody());
    }


    @Test
    public void testGetAllDocuments() {
        List<DocumentTypeResponse> documentTypeResponses = Collections.singletonList(new DocumentTypeResponse());
        when(crudInterface.findAll()).thenReturn(documentTypeResponses);

        ResponseEntity<Object> response = documentTypeController.getAllDocuments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(documentTypeResponses, response.getBody());
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMINISTRADOR")
    public void testDeleteDocument() {
        doNothing().when(crudInterface).delete(anyLong());

        ResponseEntity<Object> response = documentTypeController.deleteDocument(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Documento eliminado correctamente", response.getBody());
    }

}
