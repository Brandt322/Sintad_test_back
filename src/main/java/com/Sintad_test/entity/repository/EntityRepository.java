package com.Sintad_test.entity.repository;

import com.Sintad_test.entity.models.entities.TbEntity;
import com.Sintad_test.entity.models.response.EntityBasicResponse;
import com.Sintad_test.entity.models.response.EntityDetailsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntityRepository extends JpaRepository<TbEntity, Long> {
    @Query(
            "SELECT new com.Sintad_test.entity.models.response.EntityBasicResponse(" +
                    "e.id, e.documentNumber, e.companyName, e.tradeName, e.state, " +
                    "new com.Sintad_test.taxpayerType.models.response.TaxpayerTypeBasicResponse(tax.id, tax.name, tax.state), " +
                    "new com.Sintad_test.documentType.models.response.DocumentTypeBasicResponse(doc.id, doc.name)) " +
                    "FROM TbEntity e " +
                    "LEFT JOIN e.tbTaxpayerType tax " +
                    "LEFT JOIN e.tbDocumentType doc " +
                    "WHERE (:documentType IS NULL OR doc.id = :documentType) " +
                    "AND (tax IS NULL OR tax.state = true) " +
                    "AND doc.state = true " +
                    "AND (:taxpayerType IS NULL OR tax.id = :taxpayerType) " +
                    "AND (:state IS NULL OR e.state = :state)" +
                    "ORDER BY e.id DESC"
    )
    Page<EntityBasicResponse> findEntityForPagination(@Param("documentType") Long documentType, @Param("taxpayerType") Long taxpayerType, @Param("state") Boolean state, Pageable pageable);


    @Query(
            "SELECT new com.Sintad_test.entity.models.response.EntityDetailsResponse(" +
                    "e.id, e.documentNumber, e.companyName, e.tradeName, e.state, e.address, e.cellphone, " +
                    "new com.Sintad_test.taxpayerType.models.response.TaxpayerTypeBasicResponse(tax.id, tax.name, tax.state), " +
                    "new com.Sintad_test.documentType.models.response.DocumentTypeBasicResponse(doc.id, doc.name)) " +
                    "FROM TbEntity e " +
                    "LEFT JOIN e.tbTaxpayerType tax " +
                    "LEFT JOIN e.tbDocumentType doc " +
                    "WHERE e.id = :id"
    )
    Optional<EntityDetailsResponse> findEntityById(Long id);
}
