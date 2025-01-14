package com.Sintad_test.taxpayerType.interfaces;

import com.Sintad_test.taxpayerType.models.entities.TbTaxpayerType;
import com.Sintad_test.taxpayerType.models.request.TaxpayerTypeBasicRequest;
import com.Sintad_test.taxpayerType.models.response.TaxpayerTypeBasicResponse;

public interface TaxpayerType {
    TbTaxpayerType dtoToEntity(TaxpayerTypeBasicRequest request);
    TaxpayerTypeBasicResponse entityToDto(TbTaxpayerType entity);
}
