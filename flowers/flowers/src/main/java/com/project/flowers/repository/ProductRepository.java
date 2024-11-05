package com.project.flowers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.flowers.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    
}
