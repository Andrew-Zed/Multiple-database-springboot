package com.andrew.springdatajpa.product.repos;

import com.andrew.springdatajpa.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {


}
