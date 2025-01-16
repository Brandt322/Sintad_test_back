package com.Sintad_test.entity.models.response;

import com.Sintad_test.documentType.models.response.DocumentTypeBasicResponse;
import com.Sintad_test.shared.interfaces.IHandlePaginationResponse;
import com.Sintad_test.taxpayerType.models.response.TaxpayerTypeBasicResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntityBasicResponse implements IHandlePaginationResponse {
    private Long id;
    private String documentNumber;
    private String companyName;
    private String tradeName;
    private Boolean state;
    private TaxpayerTypeBasicResponse taxpayerTypeBasicResponse;
    private DocumentTypeBasicResponse documentTypeBasicResponse;
}
