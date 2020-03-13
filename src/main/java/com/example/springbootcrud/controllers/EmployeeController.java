package com.example.springbootcrud.controllers;

import com.example.springbootcrud.models.Employee;
import com.example.springbootcrud.models.EmployeeDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeDao employeeDao;
	
	@RequestMapping("/getall")
	@ResponseBody
	public List<Employee> getAll() {
		return employeeDao.getAll();
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, consumes="application/json")
	@ResponseBody
	public ResponseEntity<Employee> getById(@PathVariable("id") int id) {
		Employee emp = null;
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Responded", "EmployeeController");
        
		try {
			emp = employeeDao.getById(id);
		} catch(Exception ex) {
			 System.out.println("Employee not found" + ex.getMessage());
			 return ResponseEntity.accepted().headers(headers).body(emp);
		}
		
		return ResponseEntity.ok().headers(headers).body(emp);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public ResponseEntity<Employee> create(@RequestBody Employee postdata) {
		Employee emp = null;
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Responded", "EmployeeController");
        
		try {
			emp = employeeDao.create(postdata);
		} catch(Exception ex) {
			 System.out.println("Employee not found" + ex.getMessage());
			 return ResponseEntity.ok().headers(headers).body(emp);
		}
		
		return ResponseEntity.ok().headers(headers).body(emp);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, consumes="application/json")
	@ResponseBody
	public String delete(@PathVariable("id") int id) {
		boolean isDeleted = false;
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Responded", "EmployeeController");
        
		try {
			Employee emp = new Employee(id);
			isDeleted = employeeDao.delete(emp);
		} catch(Exception ex) {
			 System.out.println("Employee not found to delete" + ex.getMessage());
			 return "Error deleting the Employee: " + ex.toString();
		}
		
		if(isDeleted) {
			return "Employee succesfully deleted!";
		}
		return "Error! Employee deleted!";
	}
}