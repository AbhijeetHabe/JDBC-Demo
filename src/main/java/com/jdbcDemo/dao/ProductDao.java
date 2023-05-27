package com.jdbcDemo.dao;

import java.util.List;
import com.jdbcDemo.pojo.*;

public interface ProductDao {
	
	// Query operations
	List<Product> getAllProducts();
	Product searchProduct(int id);
	
	// CRUD Operations
	boolean addNewProduct(Product product);
	boolean updateProduct(Product product);
	boolean deleteProduct(int id);
}
