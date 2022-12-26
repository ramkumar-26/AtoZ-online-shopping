package com.atoz.service;


import java.util.List;

import com.atoz.exception.CustomerException;
import com.atoz.exception.LoginException;
import com.atoz.exception.ProductException;
import com.atoz.model.Product;
import com.atoz.model.ProductDto;

public interface ProductService {
	
	
	public List<Product> viewAllProductsService() throws ProductException;

	public Product viewProductByIdSerivce(Integer id) throws ProductException;

	public Product addProductService(Product product,String key) throws ProductException, LoginException, CustomerException;

	public Product updateProductService(Product product,String key) throws ProductException, CustomerException;

	public List<Product> viewProductByCategoryService(String c_name) throws ProductException;

	public Product removeProductService(Integer id,String key) throws ProductException, CustomerException;
	
	
	

}

