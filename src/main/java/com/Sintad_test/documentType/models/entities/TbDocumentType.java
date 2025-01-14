package com.Sintad_test.documentType.models.entities;

import com.Sintad_test.entity.models.entities.TbEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_tipo_documento")
@Entity
public class TbDocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_documento")
    private Long id;

    @Column(name = "codigo", nullable = false, length = 20)
    private String code;

    @Column(name = "nombre", nullable = false, length = 100)
    private String name;

    @Column(name = "descripcion", length = 200)
    private String description;

    @Column(name = "estado", nullable = false)
    private Boolean state;

    @JsonIgnore
    @OneToMany(mappedBy = "tbDocumentType")
    @JsonManagedReference
    private List<TbEntity> entities;
}
