package com.example.Material.Stock.Management.requestrepository;

import com.example.Material.Stock.Management.domainrequest.MaterialRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

//To perform db operations
@Repository
public interface MaterialRequestRepository extends JpaRepository<MaterialRequest,Integer> {
//    @Transactional //For DML Operations
//    @Modifying //For DML Operations
//    @Query("insert into MaterialRequest values mId=:mId, mName=:mName, mQuantity=:mQuantity")
//    void deleteCustomer(@Param(value = "mId") int mId,@Param(value = "mName") String mName,@Param(value = "mQuantity") int mQuantity);

}
