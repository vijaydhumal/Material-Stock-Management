package com.example.Material.Stock.Management.requestrepository;

import com.example.Material.Stock.Management.domainrequest.MaterialRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//To perform db operations
@Repository
public interface MaterialRequestRepository extends JpaRepository<MaterialRequest,Integer> {
}
