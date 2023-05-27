package com.jdbcDemo.main;

import java.util.List;
import java.util.Scanner;

import com.jdbcDemo.dao.ProductDao;
import com.jdbcDemo.daoImpl.ProductDaoImpl;
import com.jdbcDemo.pojo.Product;

public class AppMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ProductDao daoImpl = new ProductDaoImpl();
		
		while(true) {
			System.out.println("Menu:");
            System.out.println("1. Insert a row");
            System.out.println("2. Delete a row");
            System.out.println("3. Update a row");
            System.out.println("4. Show all records");
            System.out.println("5. Search a record with ID");
            System.out.println("6. Exit");
            System.out.println("Enter your choice: ");
            int opt = sc.nextInt();
            
            switch(opt) {
            case 1:
            	System.out.println("Enter product name");
            	String name = sc.next();
                System.out.println("Enter product price");
                int price = sc.nextInt();
            	Product pro = new Product();
            	pro.setPname(name);
            	pro.setPrice(price);
            	daoImpl.addNewProduct(pro);
            	break;
            	
            case 2:
            	System.out.println("Enter product id");
                int pid = sc.nextInt();
            	daoImpl.deleteProduct(pid);
            	break;
            	
            case 3:
            	Product pro1 = new Product();
            	System.out.println("Enter product id");
                int pid1 = sc.nextInt();
                System.out.println("Enter product name");
                String pname = sc.next();
                System.out.println("Enter product price");
                int pprice = sc.nextInt();
                pro1.setPid(pid1);
                pro1.setPname(pname);
                pro1.setPrice(pprice);
            	daoImpl.updateProduct(pro1);
            	break;
         
            case 4:
            	List<Product> lst = daoImpl.getAllProducts();
            	for (Product item : lst) {
            		System.out.println("ID "+item.getPid());
            		System.out.println("Product Name: "+item.getPname());
            		System.out.println("Product price: "+item.getPrice());
            		System.out.println();
            	}
            	break;
            	
            case 5:
            	System.out.println("Enter product id");
            	int spid = sc.nextInt();
            	Product item = daoImpl.searchProduct(spid);
            	System.out.println("Product Name: "+item.getPname());
        		System.out.println("Product price: "+item.getPrice());
        		System.out.println();
            	break;
            
            case 6:
            	System.out.println("Exiting the program...");
            	System.exit(0);
            	break;
            }
		}
		
	}

}
