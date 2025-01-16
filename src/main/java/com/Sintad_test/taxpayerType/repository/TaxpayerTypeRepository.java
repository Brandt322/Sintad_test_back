package com.Sintad_test.taxpayerType.repository;

import com.Sintad_test.taxpayerType.models.entities.TbTaxpayerType;
import com.Sintad_test.taxpayerType.models.response.TaxpayerBasicListResponse;
import com.Sintad_test.taxpayerType.models.response.TaxpayerTypeBasicResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaxpayerTypeRepository extends JpaRepository<TbTaxpayerType, Long> {
    @Query(
        "SELECT new com.Sintad_test.taxpayerType.models.response.TaxpayerTypeBasicResponse(t.id, t.name, t.state) " +
        "FROM TbTaxpayerType t " +
        "WHERE t.id = ?1"
    )
    Optional<TaxpayerTypeBasicResponse> findTaxpayerTypeById(Long id);

    @Query(
        "SELECT new com.Sintad_test.taxpayerType.models.response.TaxpayerTypeBasicResponse(t.id, t.name, t.state) " +
        "FROM TbTaxpayerType t"
    )
    List<TaxpayerTypeBasicResponse> findAlltoList();

    @Query(
        "SELECT new com.Sintad_test.taxpayerType.models.response.TaxpayerBasicListResponse(t.id, t.name) " +
        "FROM TbTaxpayerType t " +
        "WHERE t.state = true " +
        "ORDER BY t.name ASC"
    )
    List<TaxpayerBasicListResponse> findBasicTaxpayerType();
}
