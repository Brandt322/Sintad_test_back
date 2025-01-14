package com.Sintad_test.taxpayerType.models.entities;

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
@Table(name = "tb_entidad")
@Entity
public class TbTaxpayerType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_contribuyente")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String name;

    @Column(name = "estado", nullable = false)
    private Boolean state;

    @JsonIgnore
    @OneToMany(mappedBy = "tbTaxpayerType")
    @JsonManagedReference
    private List<TbEntity> entities;
}
