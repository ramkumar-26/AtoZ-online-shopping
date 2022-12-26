package com.atoz.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.atoz.exception.CartException;
import com.atoz.exception.CustomerException;
import com.atoz.exception.LoginException;
import com.atoz.exception.ProductException;
import com.atoz.model.Cart;
import com.atoz.model.Product;
import com.atoz.model.ProductDto;


@Service
public interface CartServices {

   
	
	public ProductDto updateProductQuantity( Integer pid, Integer quantity, String key) throws CustomerException, LoginException ;
	
        public ProductDto DeleteproductFromCart(Integer pDtoid, String key)throws CustomerException, LoginException, ProductException;
	
	public Cart addProductToCart(Integer pid,  Integer quantity, String key) throws CustomerException, LoginException, ProductException;
	
	public List<ProductDto> viewAllProductsFromCart(String key) throws CustomerException, LoginException, ProductException;
	
	public double cartTotal(String key)throws CustomerException, LoginException, ProductException;
	
	}

	

