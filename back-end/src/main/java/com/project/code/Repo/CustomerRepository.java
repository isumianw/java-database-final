package com.project.code.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.code.Model.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    Customer findByEmail(String email);

    Customer findById(Long id);

    List<Customer> findByName(String name);

}


