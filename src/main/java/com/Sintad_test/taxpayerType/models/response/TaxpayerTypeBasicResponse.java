package com.Sintad_test.taxpayerType.models.response;

import com.Sintad_test.shared.interfaces.IHandleCrudResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaxpayerTypeBasicResponse implements IHandleCrudResponse {
    private Integer id;
    private String name;
    private Boolean state;
}
