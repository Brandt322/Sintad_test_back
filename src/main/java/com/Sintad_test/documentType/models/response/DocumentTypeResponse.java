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

    @Column(name = "codigo", nullable = false, length = 20)
    private String code;

    @Column(name = "nombre", nullable = false, length = 100)
    private String name;

    @Column(name = "descripcion", length = 200)
    private String description;
}
