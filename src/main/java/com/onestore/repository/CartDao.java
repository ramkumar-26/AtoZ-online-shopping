package com.onestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.onestore.exception.ProductException;
import com.onestore.model.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer>{
   
	@Query(value="delete from  product_dto  c where c.id =?1",nativeQuery = true)
	public void deletefromCartbyProdId (Integer productId) throws ProductException;
	
}
