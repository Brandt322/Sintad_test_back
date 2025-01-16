package com.Sintad_test.entity.models.entities;

import com.Sintad_test.documentType.models.entities.TbDocumentType;
import com.Sintad_test.taxpayerType.models.entities.TbTaxpayerType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_entidad")
@Entity
public class TbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entidad")
    private Long id;

    @Column(name = "nro_documento", nullable = false, unique = true, length = 25)
    @NotNull(message = "Numero de documento es requerido")
    private String documentNumber;

    @Column(name = "razon_social", nullable = false, length = 100)
    @NotNull(message = "Razon social es requerido")
    private String companyName;

    @Column(name = "nombre_comercial", length = 100)
    private String tradeName;

    @Column(name = "direccion", length = 250)
    private String address;

    @Column(name = "telefono", length = 50)
    private String cellphone;

    @Column(name = "estado", nullable = false)
    @NotNull(message = "Estado es requerido")
    private Boolean state;

    @ManyToOne
    @JoinColumn(name = "id_tipo_contribuyente", nullable = true)
    @JsonBackReference
    private TbTaxpayerType tbTaxpayerType;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento", nullable = false)
    @JsonBackReference
    private TbDocumentType tbDocumentType;
}
