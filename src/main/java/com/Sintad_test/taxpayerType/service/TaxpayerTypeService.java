package com.Sintad_test.taxpayerType.service;

import com.Sintad_test.exceptions.BadRequestException;
import com.Sintad_test.exceptions.NotFoundException;
import com.Sintad_test.shared.interfaces.Crud;
import com.Sintad_test.taxpayerType.interfaces.TaxpayerType;
import com.Sintad_test.taxpayerType.models.entities.TbTaxpayerType;
import com.Sintad_test.taxpayerType.models.request.TaxpayerTypeBasicRequest;
import com.Sintad_test.taxpayerType.models.response.TaxpayerTypeBasicResponse;
import com.Sintad_test.taxpayerType.repository.TaxpayerTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaxpayerTypeService implements Crud<TaxpayerTypeBasicRequest, TaxpayerTypeBasicResponse> {
    private final TaxpayerTypeRepository taxpayerTypeRepository;
    private final TaxpayerType taxpayerType;

    public TaxpayerTypeService(TaxpayerTypeRepository taxpayerTypeRepository, TaxpayerType taxpayerType) {
        this.taxpayerTypeRepository = taxpayerTypeRepository;
        this.taxpayerType = taxpayerType;
    }

    @Override
    public List<TaxpayerTypeBasicResponse> findAll() {
        return taxpayerTypeRepository.findAlltoList();
    }

    @Override
    public TaxpayerTypeBasicResponse findById(Long id) {
        Optional<TaxpayerTypeBasicResponse> taxpayerTypeBasicResponse = taxpayerTypeRepository.findTaxpayerTypeById(id);
        if ((taxpayerTypeBasicResponse.isEmpty())) {
            throw new NotFoundException("Taxpayer type not found with id: " + id);
        }
        return taxpayerTypeBasicResponse.get();
    }

    @Override
    public TaxpayerTypeBasicResponse create(TaxpayerTypeBasicRequest request) {

        TbTaxpayerType tbTaxpayerType = taxpayerType.dtoToEntity(request);
        taxpayerTypeRepository.save(tbTaxpayerType);

        return taxpayerType.entityToDto(tbTaxpayerType);
    }

    @Override
    public TaxpayerTypeBasicResponse update(Long id, TaxpayerTypeBasicRequest request) {
        TbTaxpayerType tbTaxpayerType = taxpayerTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Taxpayer type not found with id: " + id));
        tbTaxpayerType.setName(request.getName());
        tbTaxpayerType.setState(request.getState());
        taxpayerTypeRepository.save(tbTaxpayerType);

        return taxpayerType.entityToDto(tbTaxpayerType);
    }

    @Override
    public void delete(Long id) {
        if(id == null || id == 0){
            throw new BadRequestException("Id is empty");
        }

        Optional<TaxpayerTypeBasicResponse> tbTaxpayerTypeBasicResponse = taxpayerTypeRepository.findTaxpayerTypeById(id);

        if (tbTaxpayerTypeBasicResponse.isEmpty()) {
            throw new NotFoundException("Taxpayer type not found with id: " + id);
        }

        taxpayerTypeRepository.deleteById(id);
    }
}
