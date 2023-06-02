package com.kavya;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Donor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String bg;
	private String mobileno;
	public Donor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Donor(int id,String name, String bg, String mobileno) {
		super();
		this.id = id;
		this.name = name;
		this.bg = bg;
		this.mobileno = mobileno;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBg() {
		return bg;
	}
	public void setBg(String bg) {
		this.bg = bg;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	@Override
	public String toString() {
		return "Donor [id=" + id + ", name=" + name + ", bg=" + bg + ", mobileno=" + mobileno + "]";
	}
	
	
	
}
