package com.Sintad_test.documentType.repository;

import com.Sintad_test.documentType.models.entities.TbDocumentType;
import com.Sintad_test.documentType.models.response.DocumentTypeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentTypeRepository extends JpaRepository<TbDocumentType, Long> {

    @Query(
        "SELECT new com.Sintad_test.documentType.models.response.DocumentTypeResponse(d.id, d.code, d.name, d.description, d.state) " +
        "FROM TbDocumentType d " +
        "WHERE d.id = ?1"
    )
    Optional<DocumentTypeResponse> findDocumentTypeById(Long id);

    @Query(
        "SELECT new com.Sintad_test.documentType.models.response.DocumentTypeResponse(d.id, d.code, d.name, d.description, d.state) " +
        "FROM TbDocumentType d ORDER BY CAST (d.code AS int) DESC"
    )
    List<DocumentTypeResponse> findAlltoList();
}
