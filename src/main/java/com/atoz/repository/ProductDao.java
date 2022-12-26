package com.atoz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atoz.model.Order;
import com.atoz.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer>{
	
	public List<Product> findByCategory(String category);

}
