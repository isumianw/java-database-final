package com.project.code.Repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.project.code.Model.Store;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{

    Store findById(Long id);

    @Query("SELECT p FROM Store p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :pname, '%'))")
    List<Store> findBySubName(String pname);
   
}
