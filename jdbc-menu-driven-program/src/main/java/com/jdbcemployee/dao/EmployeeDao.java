package com.jdbcemployee.dao;

import java.util.List;

import com.jdbcemployee.pojo.Employee;

public interface EmployeeDao {

	List<Employee> getAllData();
	
	boolean insertAllData(Employee emp);
	
	void getAddressWithId(int id);
	void getEmployeeWithCity(String city);
	void getEmployeeWithHighestSalary();
}
