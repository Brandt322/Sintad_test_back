package com.Sintad_test.taxpayerType.models.mapper;

import com.Sintad_test.taxpayerType.interfaces.TaxpayerType;
import com.Sintad_test.taxpayerType.models.entities.TbTaxpayerType;
import com.Sintad_test.taxpayerType.models.request.TaxpayerTypeBasicRequest;
import com.Sintad_test.taxpayerType.models.response.TaxpayerTypeBasicResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TaxpayerTypeMapper implements TaxpayerType {
    private final ModelMapper modelMapper;

    public TaxpayerTypeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TbTaxpayerType dtoToEntity(TaxpayerTypeBasicRequest request) {
        return modelMapper.map(request, TbTaxpayerType.class);
    }

    @Override
    public TaxpayerTypeBasicResponse entityToDto(TbTaxpayerType entity) {
        return modelMapper.map(entity, TaxpayerTypeBasicResponse.class);
    }
}
