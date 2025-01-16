package com.Sintad_test.documentType.interfaces;

import com.Sintad_test.documentType.models.response.DocumentTypeBasicResponse;

import java.util.List;

public interface DocumentTypeList {
    List<DocumentTypeBasicResponse> findBasicDocumentType();
}
