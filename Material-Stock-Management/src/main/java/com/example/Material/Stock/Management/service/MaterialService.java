package com.example.Material.Stock.Management.service;

import com.example.Material.Stock.Management.domain.Material;
import com.example.Material.Stock.Management.domainrequest.MaterialRequest;
import com.example.Material.Stock.Management.repository.MaterialRepository;
import com.example.Material.Stock.Management.requestrepository.MaterialRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {
    //setter injection
    @Autowired  //Inject dependencies
    private MaterialRepository repository;
    //setter injection
    @Autowired
    private MaterialRequestRepository requestRepository;

    List<Material> materialList;

    List<MaterialRequest> materialRequestList;

    // Add New Material
    public void addMaterial(Material material) {
        repository.save(material);
    }

    // Get All Materials
    public List<Material> getAllMaterials(){
        materialList =repository.findAll();
        return materialList;
    }

    //Display material by Id
    public Material getMaterialById(int id){
        Material material=repository.findById(id).orElse(null);
        return material;
    }

    //Update existing material details by Id
    public void updateProduct(Material material){
        repository.save(material);
    }

    //Add Requested Material
        public void addRequestMaterial(MaterialRequest materialRequest) {
            requestRepository.save(materialRequest);
       }

       // get All Requsted Materials
       public List<MaterialRequest> getAllRequestedMaterials(){
           materialRequestList = requestRepository.findAll();
           return materialRequestList;
       }

       //approve materials
       public void approveMaterial(MaterialRequest materialRequest) {
           Material material = repository.findById(materialRequest.getMId()).get();
           int requestedQuantity = materialRequest.getMQuantity();
           int updatedQuantity = material.getMaterialQuantity() - requestedQuantity;
           material.setMaterialQuantity(updatedQuantity);
           repository.save(material);
       }

}
