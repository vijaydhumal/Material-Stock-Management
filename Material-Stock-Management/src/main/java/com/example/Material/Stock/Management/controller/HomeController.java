package com.example.Material.Stock.Management.controller;

import com.example.Material.Stock.Management.domain.Material;
import com.example.Material.Stock.Management.domainrequest.MaterialRequest;
import com.example.Material.Stock.Management.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @Autowired
    private MaterialService service;

    //Get the Material Form
    @GetMapping("/addmaterial")
    public String getMaterialForm(Model model){
        Material material=new Material();
        model.addAttribute("material",material);
        return "addmaterial";
    }

    //Add Materials
    @PostMapping("/insertmaterial")
    @ResponseBody
    public String addMaterials(Material material){
        service.addMaterial(material);
        return "Value Inserted Successfully";
    }

    //Get All the Materials
    @GetMapping("/siteincharge")
    public String getAllMaterials(Model model){
        model.addAttribute("material",service.getAvailableMaterials());
        return "siteincharge";
    }

    //landing page
    @GetMapping("/")
    public String getHomePage() {
        return "home";
    }

    //Go to the storeincharge page
    @GetMapping("/storeincharge")
    public String getStoreInchargePage(Model model){
        model.addAttribute("material",service.getAllMaterials());
        return "storeincharge";
    }

    //Get Requested Form
    @GetMapping("/request")
    public String getRequestPage(Model model) {
        model.addAttribute("materialRequest",new MaterialRequest());
        return "request";
    }
    //Add Requested Materials
    @ResponseBody
    @PostMapping("/requestmaterial")
    public String addRequestMaterials(MaterialRequest materialRequest){
        service.addRequestMaterial(materialRequest);
        return "Material Requsted";
    }

    //View all requested materials
    @GetMapping("/viewmaterial")
    public String getRequestedMaterials(Model model){
        model.addAttribute("material",service.getAllRequestedMaterials());
        return "viewmaterial";
    }

   //Get UpdateMaterial form
    @GetMapping("updatematerial/{id}")
    public String getMaterialUpdateForm(@PathVariable(name = "id") int id, Model model){
        model.addAttribute("material",service.getMaterialById(id));
        return "updatematerial";
    }

    //Update Materilas Quantity
    @ResponseBody
    @PostMapping("modifymaterial")
    public String modifyMaterial(Material material){
        service.updateProduct(material);
        return "Material Quantity Updated Successfully";
    }

    //Approve Material
    @ResponseBody
    @GetMapping("/approvematerial/{id}")
    public String callDemo1(@PathVariable int id) {
        boolean isMaterialApproved = service.callDemo1Procedure(id);

        if (isMaterialApproved) {
            return "Material Request Approved";
        }else {
            return "Insufficient Quantity";
        }
    }

    @GetMapping("deletematerial/{id}")
    public String deleteMaterial(@PathVariable(name = "id") int id){
        service.deleteMaterial(id);
        return "redirect:/storeincharge";
    }


}
