package com.example.springbootcrud.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
public class Employee {
	
	  // ------------------------
	  // PRIVATE FIELDS
	  // ------------------------
	  
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int id;
	  
	  @NotNull
	  private String employee_name;
	  
	  @NotNull
	  private String employee_role;
	  
	  
	  public Employee() { }
	  
	  public Employee(int id) { 
	    this.id = id;
	  }

	  public Employee(String employee_name, String employee_role) {
	    this.employee_name = employee_name;
	    this.employee_role = employee_role;
	  }
	  public String getName() {
		  return employee_name;
	  }
	  public void setName(String name) {
		  this.employee_name = name;
	  }
	  public String getEmployeeRole() {
		  return employee_role;
	  }
	  public void setEmployeeRole(String role) {
		  this.employee_role = role;
	  }

	  public void setId(int value) {
		  this.id = value;
	  }
	  public int getId() {
	    return id;
	  }
	
}