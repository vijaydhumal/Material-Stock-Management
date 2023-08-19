package com.example.Material.Stock.Management.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//Configuration through annotations

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name= "material_info")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private int materialId;
    @Column(name = "material_name")
    private String materialName;
    @Column(name = "material_quantity")
    private int materialQuantity;
    @Column(name = "material_unit")
    private String materialUnit;

}