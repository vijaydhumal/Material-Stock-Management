package com.example.Material.Stock.Management.repository;

import com.example.Material.Stock.Management.domain.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//To perform db operations
@Repository
public interface MaterialRepository extends JpaRepository<Material,Integer> {
}
