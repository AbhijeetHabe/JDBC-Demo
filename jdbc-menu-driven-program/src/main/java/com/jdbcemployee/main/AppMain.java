package com.jdbcemployee.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jdbcemployee.dao.EmployeeDao;
import com.jdbcemployee.daoImpl.EmployeeDaoImpl;
import com.jdbcemployee.pojo.Employee;

public class AppMain {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		EmployeeDao emp = new EmployeeDaoImpl();
		
		while(true) {
			System.out.println("Menu:");
	        System.out.println("1. Select the employee records with their address");
	        System.out.println("2. Select the address of an employee whose employee id is given.");
	        System.out.println("3. Select all the employees who are in the given city. ");
	        System.out.println("4. Select the employee who gets highest salary. ");
	        System.out.println("5. Select all the employees who has experience more than 5 years.");
	        System.out.println("6. Exit");
	        System.out.println("Enter your choice: ");
	        int opt = sc.nextInt();
	        
	        switch (opt) {
			case 1:
				List<Employee> lst = new ArrayList<>();
				lst = emp.getAllData();
				for (Employee item : lst) {
					System.out.println("ID : "+item.getEid());
					System.out.println("Name: "+item.getName());
					System.out.println("Salary : "+item.getSalary());
					System.out.println("Date of join: "+item.getJoindate());
					System.out.println("City :"+item.getCity());
					System.out.println("Country : "+item.getCountry());
				}
				break;
			
			case 2:
				Employee employee = new Employee();
				System.out.println("Enter Employee Id: ");
                int empid = sc.nextInt();
                employee.setEid(empid);
                System.out.println("Enter Employee Name: ");
                String name = sc.next();
                employee.setName(name);
                System.out.println("Enter Employee Salary: ");
                int salary = sc.nextInt();
                employee.setSalary(salary);
                System.out.println("Enter Employee Joindate: ");
                String joindate = sc.next();
                employee.setJoindate(joindate);
            	System.out.println("Enter Employee's Address Id: ");
                int addid = sc.nextInt();
                employee.setAid(addid);
                System.out.println("Enter Employee's City: ");
                String city = sc.next();
                employee.setCity(city);
                System.out.println("Enter Employee's Country: ");
                String country = sc.next();
                employee.setCountry(country);
                if(emp.insertAllData(employee));
        			System.out.println("Employee saved successfully");
				break;
			
			case 3:
				System.out.println("Enter Employee Id: ");
                int empid1 = sc.nextInt();
                
                emp.getAddressWithId(empid1);
                
			case 4:
				emp.getEmployeeWithHighestSalary();
				
			case 6:
				System.out.println("Exiting the program...");
				System.exit(0);

			default:
				break;
			}
		}
	}

}
