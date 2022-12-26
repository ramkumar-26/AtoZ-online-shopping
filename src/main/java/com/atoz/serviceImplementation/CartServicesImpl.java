package com.atoz.serviceImplementation;

import java.lang.StackWalker.Option;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atoz.exception.CartException;
import com.atoz.exception.CustomerException;
import com.atoz.exception.LoginException;
import com.atoz.exception.ProductException;
import com.atoz.model.Cart;
import com.atoz.model.CurrentUserSession;
import com.atoz.model.Customer;
import com.atoz.model.Product;
import com.atoz.model.ProductDto;
import com.atoz.repository.CartDao;
import com.atoz.repository.CustomerDao;
import com.atoz.repository.ProductDao;
import com.atoz.repository.ProductDtoDao;
import com.atoz.repository.UserSessionDao;
import com.atoz.service.CartServices;


@Service
public class CartServicesImpl implements CartServices{

	@Autowired
	private ProductDtoDao productDao;
	
	@Autowired
	private CartDao cartD;
	
	@Autowired
	private UserSessionDao currentuser;
	
	@Autowired
	private CustomerDao custDao;
	
	@Autowired
	private Validation valid;
	
	@Autowired
	private ProductDao productRepo;
	
	
	@Override
	public ProductDto updateProductQuantity(Integer pDtoId, Integer quantity, String key) throws CustomerException,LoginException {
      
		Customer customer = valid.validateLogin(key);
		
		System.out.println(customer.getCart().getProducts()+"---------------------------->");
		
	List<ProductDto> productDtoList=	customer.getCart().getProducts();
		
	Optional<ProductDto> productopt =	productDao.findById(pDtoId);
	
	ProductDto prod = null;
	
	if(productopt.isPresent())
	{
		 prod = productopt.get();
		prod.setQuantity(prod.getQuantity()+quantity);
		productDao.save(prod);
	}
	else
	{
		throw new CustomerException("Product not found in ProductDto table with Id:"+pDtoId);
	}
	
	    if(prod==null)
	    {
		throw new CustomerException("product not available...");
	     }
	      else
	      {
		return prod;
	      }
	
		
				
	}
		
			

         @Override
	public Cart addProductToCart(Integer pid,Integer quantity, String key) throws CustomerException, LoginException, ProductException {
		   
		Optional<Product> prodopt =productRepo.findById(pid);
		
		Customer customer = valid.validateLogin(key);
		
		if(prodopt.isEmpty()) {
			throw new ProductException("Product Not Available!");
		}
		
		Cart cust_cart =  customer.getCart();
		
         Product product =prodopt.get();
         
         if(product.getQuantity()==0)
			{
				throw new ProductException("Product is out of stock");
			}
         
         if(product.getQuantity()<quantity)
         {
        	 throw new ProductException("Quantity you were enter is more than available Quantity="+product.getQuantity()+
        			                   " ,please provide valid quantity for selected product");
         }
		
	 List<ProductDto> existProducts = cust_cart.getProducts();
		
		boolean flag = false;
		for(ProductDto ele:existProducts) {
			if(ele.getProductId()==pid) {
				ele.setQuantity(ele.getQuantity()+quantity);
				
				product.setQuantity(product.getQuantity()-quantity);
				productRepo.save(product);
				
				flag = true;
				break;
			}
			
		}
		
		if(!flag) {
			ProductDto dto = new ProductDto();
			
		dto.setColor(product.getColor());
		dto.setDimension(product.getDimension());
		dto.setManufacturer(product.getManufacturer());
		dto.setPrice(product.getPrice());
		dto.setProductId(product.getProductId());
		dto.setProductName(product.getProductName());
		dto.setQuantity(quantity);
		cust_cart.getProducts().add(dto);
		
		product.setQuantity(product.getQuantity()-quantity);
		productRepo.save(product);
		}
		
		
		
		Cart updatedCart = cartD.save(cust_cart);
		
		customer.setCart(updatedCart);
		
		return updatedCart;
	}
	

	
	

        @Override
	public List<ProductDto> viewAllProductsFromCart(String key) throws CustomerException, LoginException, ProductException {
		Customer customer = valid.validateLogin(key);
		 
		List<ProductDto> products=        customer.getCart().getProducts();
			
			
			products = productDao.findAll();
			
			if(products.isEmpty()) {
				
				throw new ProductException("empty list of products");
			}
			
			return products;
			
		
	}





	@Override
	public double cartTotal(String key)throws CustomerException, LoginException, ProductException{
		
		Customer customer = valid.validateLogin(key);
		if(customer==null)
		{
			throw new CustomerException("Please provide valid key or ur Admin");
		}
		
		double price = 0;
		   
		List<ProductDto> products    =    customer.getCart().getProducts();
		
		 products = productDao.findAll();
		
		if(products.isEmpty()) {
			
			throw new ProductException("list of products  is empty .........");
		}
		else
		{
			for(int i=0;i<products.size();i++)
			{
				price = price + products.get(i).getPrice();
			}
		}
		
		
		
		
		return price;
	}
	
	
	
	@Override
	public ProductDto DeleteproductFromCart(Integer pDtoid, String key)throws CustomerException, LoginException, ProductException {
		
	Optional<ProductDto> prodopt=	productDao.findById(pDtoid);
	
	ProductDto product=null;
	
	if(prodopt.isPresent())
	{
	   product =	prodopt.get();
	  
	  productDao.delete(product);
	}
	if(product==null)
	{
		throw new ProductException("Product Not available in cart table with id:"+pDtoid);
	
	}
	else
	return product;
		
	}

}
