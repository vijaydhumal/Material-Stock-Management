package com.example.Material.Stock.Management.repositoryavailable;

import com.example.Material.Stock.Management.domainAvailable.MaterialAvailable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//To perform db operations
@Repository
public interface AvailableRepository extends JpaRepository<MaterialAvailable,Integer> {

}
