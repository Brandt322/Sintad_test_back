package com.Sintad_test.service;

import com.Sintad_test.documentType.interfaces.DocumentType;
import com.Sintad_test.documentType.models.entities.TbDocumentType;
import com.Sintad_test.documentType.models.request.DocumentTypeRequest;
import com.Sintad_test.documentType.models.response.DocumentTypeResponse;
import com.Sintad_test.documentType.repository.DocumentTypeRepository;
import com.Sintad_test.documentType.service.DocumentTypeService;
import com.Sintad_test.exceptions.BadRequestException;
import com.Sintad_test.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class DocumentTypeServiceTest {
    @Mock
    private DocumentTypeRepository documentTypeRepository;

    @Mock
    private DocumentType documentType;

    @InjectMocks
    private DocumentTypeService documentTypeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById_NotFoundException() {
        when(documentTypeRepository.findDocumentTypeById(anyLong())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            documentTypeService.findById(100L);
        });

        assertEquals("Documento no encontrado con el:  100", exception.getMessage());
    }

    @Test
    public void testCreate_Success() {
        DocumentTypeRequest request = new DocumentTypeRequest();
        TbDocumentType tbDocumentType = new TbDocumentType();
        DocumentTypeResponse response = new DocumentTypeResponse();

        when(documentType.dtoToEntity(request)).thenReturn(tbDocumentType);
        when(documentTypeRepository.save(tbDocumentType)).thenReturn(tbDocumentType);
        when(documentType.entityToDto(tbDocumentType)).thenReturn(response);

        DocumentTypeResponse result = documentTypeService.create(request);

        assertNotNull(result);
        assertEquals(response, result);
    }

    @Test
    public void testDelete_BadRequestException() {
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            documentTypeService.delete(0L);
        });

        assertEquals("El id no puede estar vacío", exception.getMessage());
    }

    @Test
    public void testDelete_NotFoundException() {
        when(documentTypeRepository.findDocumentTypeById(anyLong())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            documentTypeService.delete(1L);
        });

        assertEquals("Documento no encontrado con el: 1", exception.getMessage());
    }
}
