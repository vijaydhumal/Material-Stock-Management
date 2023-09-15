package com.example.Material.Stock.Management.domainAvailable;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name= "material_available")
public class MaterialAvailable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_")
    private int materialId_;
    @Column(name = "material_name_")
    private String materialName_;
    @Column(name = "material_qty_")
    private int materialQuantity_;
    @Column(name = "material_unit_")
    private String materialUnit_;

}
