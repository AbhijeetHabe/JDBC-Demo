package com.jdbcDemo.pojo;

public class Product {
	private int pid;
	private String pname;
	private int price;
	
	public Product() {
			
		}
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return pid + " : " + pname + " : " + price;
	}
	
}
