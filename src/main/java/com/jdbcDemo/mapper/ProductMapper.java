package com.jdbcDemo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.jdbcDemo.pojo.Product;


public class ProductMapper {
	
	public static Product mapRow(ResultSet rs) throws SQLException {
		
		Product product = new Product();
		product.setPid(rs.getInt("id"));
		product.setPname(rs.getString("pname"));
		product.setPrice(rs.getInt("price"));
		return product;
	}
}
