package com.Sintad_test.documentType.models.request;

import com.Sintad_test.shared.interfaces.IHandleCrudRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTypeRequest implements IHandleCrudRequest {
    @NotNull(message = "Code is required")
    @Size(max = 20, message = "Code must be at most 20 characters")
    private String code;

    @NotNull(message = "Name is required")
    @Size(max = 100, message = "Name must not be greater than 100 characters")
    private String name;

    @Size(max = 200, message = "Maximum description length is 200 characters")
    private String description;

    private Boolean state;
}
