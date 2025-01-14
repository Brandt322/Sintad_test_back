package com.Sintad_test.documentType.models.request;

import com.Sintad_test.shared.interfaces.IHandleCrudRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTypeRequest implements IHandleCrudRequest {
    private String code;
    private String name;
    private String description;
}
