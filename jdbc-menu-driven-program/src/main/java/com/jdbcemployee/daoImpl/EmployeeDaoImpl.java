package com.jdbcemployee.daoImpl;

import java.sql.CallableStatement;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbcemployee.connection.DbConnection;
import com.jdbcemployee.dao.EmployeeDao;
import com.jdbcemployee.pojo.Employee;
import com.mysql.cj.jdbc.CallableStatement.CallableStatementParamInfo;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public List<Employee> getAllData() {
		
		List<Employee> lst = new ArrayList<>();
		try(Connection con = DbConnection.getDbConnection()) {
			
			PreparedStatement pst = con.prepareStatement("SELECT e.eid, e.name, e.salary, e.joinDate, a.city, a.country, a.aid " +
	                "FROM employee e " +
	                "INNER JOIN address a ON e.eid = a.eid");
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Employee employee = new Employee();
				employee.setEid(rs.getInt("eid"));
				employee.setName(rs.getString("name"));
				employee.setSalary(rs.getInt("salary"));
				employee.setJoindate(rs.getString("joinDate"));
				employee.setAid(rs.getInt("aid"));
				employee.setCity(rs.getString("city"));
				employee.setCountry(rs.getString("country"));
				lst.add(employee);
			}
			return lst;
			
		} catch (NullPointerException e) {
			System.out.println("Connection to database failed");
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean insertAllData(Employee emp) {
		try (Connection con = DbConnection.getDbConnection()) {
			PreparedStatement pst = con.prepareStatement("INSERT INTO employee values(?,?,?,?)");
			pst.setInt(1, emp.getEid());
			pst.setString(2, emp.getName());
			pst.setInt(3, emp.getSalary());
			pst.setString(4, emp.getJoindate());
			
			int count = pst.executeUpdate();
			
			PreparedStatement pst1 = con.prepareStatement("INSERT INTO address values(?,?,?,?)");
			pst1.setInt(1, emp.getAid());
			pst1.setString(2, emp.getCity());
			pst1.setString(3, emp.getCountry());
			pst1.setInt(4, emp.getEid());
			
			count += pst1.executeUpdate();
			
			if (count > 0) {
				return true;
			} else {
				return false;
			}
			
		} catch (NullPointerException e) {
			System.out.println("Database connection failed");
			e.printStackTrace();
			return false;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public void getAddressWithId(int id) {
		try(Connection con = DbConnection.getDbConnection()) {
			PreparedStatement pst = con.prepareStatement("Select * from address WHERE eid = ?");
			
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt("aid"));
				System.out.println(rs.getString("city"));
				System.out.println(rs.getString("country"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void getEmployeeWithCity(String city) {
		try(Connection con = DbConnection.getDbConnection()) {
			PreparedStatement pst = con.prepareStatement("Select * from employe WHERE eid IN "
					+ "(SELECT eid from address WHERE city = ?)");
			
			pst.setString(1, city);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt("eid"));
				System.out.println(rs.getString("name"));
				System.out.println(rs.getInt("salary"));
				System.out.println(rs.getString("joinDate"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void getEmployeeWithHighestSalary() {
		try(Connection con = DbConnection.getDbConnection()) {
			PreparedStatement pst = con.prepareStatement("SELECT * FROM employee WHERE salary = "
					+ "(SELECT MAX(salary) FROM employee)");
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("eid"));
				System.out.println(rs.getString("name"));
				System.out.println(rs.getInt("salary"));
				System.out.println(rs.getString("joinDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void increaseEmployeeSalary(String targetDate) {
		try (Connection con = DbConnection.getDbConnection()) {
			CallableStatement stmt = con.prepareCall("{ CALL IncreaseSalaryBeforeDate(?) }");
            stmt.setString(1, targetDate);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            // Print the data
            System.out.println("Updated employee data:");
            while (rs.next()) {
                // Print employee data
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Join Date: " + rs.getDate("join_date") +
                        ", Salary: " + rs.getDouble("salary"));
            }

		} catch (SQLException e) {
            e.printStackTrace();
        }
    }

	}

