package com.Sintad_test.documentType.models.entities;

import com.Sintad_test.entity.models.entities.TbEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "Code is required")
    @Size(max = 20, message = "Code must be at most 20 characters")
    private String code;

    @Column(name = "nombre", nullable = false, length = 100)
    @NotNull(message = "Name is required")
    @Size(max = 100, message = "Name not must be greater than 100 characters")
    private String name;

    @Column(name = "descripcion", length = 200)
    @Size(max = 100, message = "Maximum description length is 200 characters")
    private String description;

    @Column(name = "estado", nullable = false)
    @NotNull(message = "State is required")
    private Boolean state;

    @JsonIgnore
    @OneToMany(mappedBy = "tbDocumentType")
    @JsonManagedReference
    private List<TbEntity> entities;
}
