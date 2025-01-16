package com.Sintad_test.taxpayerType.interfaces;

import com.Sintad_test.taxpayerType.models.response.TaxpayerBasicListResponse;

import java.util.List;

public interface TaxpayerTypeList {
    List<TaxpayerBasicListResponse> findBasicTaxpayerType();
}
