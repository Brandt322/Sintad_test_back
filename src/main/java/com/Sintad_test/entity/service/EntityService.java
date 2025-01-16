package com.Sintad_test.entity.service;

import com.Sintad_test.documentType.models.entities.TbDocumentType;
import com.Sintad_test.documentType.repository.DocumentTypeRepository;
import com.Sintad_test.entity.interfaces.EntityBasicCrud;
import com.Sintad_test.entity.interfaces.EntityMapperMethods;
import com.Sintad_test.entity.models.entities.TbEntity;
import com.Sintad_test.entity.models.request.EntityDetailsRequest;
import com.Sintad_test.entity.models.response.EntityBasicResponse;
import com.Sintad_test.entity.models.response.EntityDetailsResponse;
import com.Sintad_test.entity.repository.EntityRepository;
import com.Sintad_test.exceptions.BadRequestException;
import com.Sintad_test.exceptions.NotFoundException;
import com.Sintad_test.shared.interfaces.Pagination;
import com.Sintad_test.shared.pagination.PagedResponse;
import com.Sintad_test.taxpayerType.models.entities.TbTaxpayerType;
import com.Sintad_test.taxpayerType.repository.TaxpayerTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Transactional
public class EntityService implements Pagination<EntityBasicResponse>, EntityBasicCrud {
    @Autowired
    private EntityRepository entityRepository;

    @Autowired
    private EntityMapperMethods entityMapperMethods;

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Autowired
    private TaxpayerTypeRepository taxpayerTypeRepository;

    @Override
    public PagedResponse<EntityBasicResponse> findAll(int page, int size, int documentType, int taxpayerType, String state) {
        Boolean stateFilter = null;
        if("activo".equalsIgnoreCase(state)) {
            stateFilter = true;
        } else if("inactivo".equalsIgnoreCase(state)) {
            stateFilter = false;
        }

        Page<EntityBasicResponse> entityBasicResponses = entityRepository.findEntityForPagination(
                documentType == 0 ? null : (long) documentType,
                taxpayerType == 0 ? null : (long) taxpayerType,
                stateFilter,
                PageRequest.of(page - 1, size)
        );

        return new PagedResponse<>(entityBasicResponses.getContent(), page, size, entityBasicResponses.getTotalElements(), entityBasicResponses.getTotalPages());
    }

    @Override
    public EntityDetailsResponse findById(Long id) {
        Optional<EntityDetailsResponse> entityBasicResponse = entityRepository.findEntityById(id);
        if (entityBasicResponse.isEmpty()) {
            throw new NotFoundException("Entidad no encontrada con el: " + id);
        }

        return entityBasicResponse.get();
    }

    @Override
    public EntityDetailsResponse create(EntityDetailsRequest request) {
        if(request == null) {
            throw new NotFoundException("La entidad no puede ser nula");
        }

        TbDocumentType tbDocumentType = documentTypeRepository.findById(request.getDocumentTypeId())
                .orElseThrow(() -> new NotFoundException("Tipo de documento no encontrado con el id: " + request.getDocumentTypeId()));

        TbTaxpayerType tbTaxpayerType = null;
        if (request.getTaxpayerTypeId() != null) {
            tbTaxpayerType = taxpayerTypeRepository.findById(request.getTaxpayerTypeId())
                    .orElseThrow(() -> new NotFoundException("Tipo de contribuyente no encontrado con el id: " + request.getTaxpayerTypeId()));
        }

        TbEntity tbEntity = entityMapperMethods.dtoToEntity(request, tbDocumentType, tbTaxpayerType);
        tbEntity.setTbDocumentType(tbDocumentType);
        tbEntity.setTbTaxpayerType(tbTaxpayerType);

        TbEntity entitySaved = entityRepository.save(tbEntity);

        return entityMapperMethods.entityToDto(entitySaved);
    }

    @Override
    public EntityDetailsResponse update(Long id, EntityDetailsRequest request) {
        TbEntity tbEntity = entityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entidad no encontrada con el id: " + id));

        TbDocumentType tbDocumentType = documentTypeRepository.findById(request.getDocumentTypeId())
                .orElseThrow(() -> new NotFoundException("Tipo de documento no encontrado con el id: " + request.getDocumentTypeId()));

        TbTaxpayerType tbTaxpayerType = null;
        if (request.getTaxpayerTypeId() != null) {
            tbTaxpayerType = taxpayerTypeRepository.findById(request.getTaxpayerTypeId())
                    .orElseThrow(() -> new NotFoundException("Tipo de contribuyente no encontrado con el id: " + request.getTaxpayerTypeId()));
        }

        tbEntity.setDocumentNumber(request.getDocumentNumber());
        tbEntity.setCompanyName(request.getCompanyName());
        tbEntity.setTradeName(request.getTradeName());
        tbEntity.setAddress(request.getAddress());
        tbEntity.setCellphone(request.getCellphone());
        tbEntity.setState(request.getState());
        tbEntity.setTbDocumentType(tbDocumentType);
        tbEntity.setTbTaxpayerType(tbTaxpayerType);

        TbEntity entitySaved = entityRepository.save(tbEntity);
        return entityMapperMethods.entityToDto(entitySaved);
    }

    @Override
    public void delete(Long id) {
        if(id == null || id == 0){
            throw new BadRequestException("El id no puede estar vacío");
        }

        Optional<EntityDetailsResponse> entityBasicResponse = entityRepository.findEntityById(id);

        if (entityBasicResponse.isEmpty()) {
            throw new NotFoundException("Entidad no encontrada con el: " + id);
        }

        entityRepository.deleteById(id);
    }
}
