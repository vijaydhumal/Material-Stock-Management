package com.example.Material.Stock.Management.domainrequest;

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
@Table(name= "material_request")
public class MaterialRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int mId;
    @Column(name = "material_name")
    private String mName;
    @Column(name = "material_quantity")
    private int mQuantity;
    @Column(name = "unit")
    private String unit;
}