package com.Sintad_test.entity.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntityDetailsRequest {
    private String documentNumber;
    private String companyName;
    private String tradeName;
    private String address;
    private String cellphone;
    private Long documentTypeId;
    private Long taxpayerTypeId;
    private Boolean state;
}
