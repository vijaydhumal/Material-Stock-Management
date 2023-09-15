package com.example.Material.Stock.Management.service;

import com.example.Material.Stock.Management.domain.Material;
import com.example.Material.Stock.Management.domainAvailable.MaterialAvailable;
import com.example.Material.Stock.Management.domainrequest.MaterialRequest;
import com.example.Material.Stock.Management.repository.MaterialRepository;
import com.example.Material.Stock.Management.repositoryavailable.AvailableRepository;
import com.example.Material.Stock.Management.requestrepository.MaterialRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MaterialService {
    //setter injection
    @Autowired  //Inject dependencies
    private MaterialRepository repository;
    //setter injection
    @Autowired
    private MaterialRequestRepository requestRepository;

    @Autowired
    private AvailableRepository availableRepository;

    List<Material> materialList;

    List<MaterialRequest> materialRequestList;

    List<MaterialAvailable> materialAvailableList;

    //approve
    private final JdbcTemplate jdbcTemplate;
    public MaterialService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //Approve button
    public boolean callDemo1Procedure(int id) {
        // Fetch values from the material_request table
        String sql = "SELECT material_name, material_quantity FROM material_request where id=" +id + "";
        List<Map<String, Object>> materialValuesList = jdbcTemplate.queryForList(sql);

        boolean isMaterialApproved = false; // Initialize to false

        for (Map<String, Object> materialValues : materialValuesList) {
            // Extract the values
            String materialName = (String) materialValues.get("material_name");
            int userQuantity = (int) materialValues.get("material_quantity");

            // Fetch materialQuantity from the material_Info table
            int materialQuantity = getMaterialQuantity(materialName);

            // Check if userQuantity is less than materialQuantity
            if (userQuantity < materialQuantity) {
                // Call the stored procedure with the fetched values
                jdbcTemplate.update("CALL demo1(?, ?)", materialName, userQuantity);
                isMaterialApproved = true; // Set to true if approved
            }
        }
        return isMaterialApproved; //Return the result
    }
    // Method to fetch materialQuantity from material_Info table
    private int getMaterialQuantity(String materialName) {
        String sql = "SELECT material_quantity FROM material_info WHERE material_name = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, materialName);
    }

    // Add New Material
    public void addMaterial(Material material) {
        repository.save(material);
    }

    // Get All Materials
    public List<Material> getAllMaterials(){
        materialList =repository.findAll();
        return materialList;
    }

    //Get All Available Materials
    public List<MaterialAvailable> getAvailableMaterials(){
        materialAvailableList = availableRepository.findAll();
        return materialAvailableList;
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

    //Get All Requsted Materials
    public List<MaterialRequest> getAllRequestedMaterials(){
        materialRequestList = requestRepository.findAll();
        return materialRequestList;
    }

    //Delete existing Material by Id
    public void deleteMaterial(int id){
        repository.deleteById(id);
    }

    // approve materials
    public void approveMaterial(MaterialRequest materialRequest) {
        Material material = repository.findById(materialRequest.getMId()).get();
        int requestedQuantity = materialRequest.getMQuantity();
        int updatedQuantity = material.getMaterialQuantity() - requestedQuantity;
        material.setMaterialQuantity(updatedQuantity);
        repository.save(material);
    }

}
