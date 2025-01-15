package com.Sintad_test.documentType.models.response;

import com.Sintad_test.shared.interfaces.IHandleCrudResponse;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTypeResponse implements IHandleCrudResponse {
    private Long id;

    private String code;

    private String name;

    private String description;

    private Boolean state;
}
