package com.example.springbootcrud.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.springbootcrud.models.Employee;

@Repository
@Transactional
public class EmployeeDao {
	@PersistenceContext
	  private EntityManager entityManager;
	  /**
	   * Return all the employee stored in the database.
	   */
	  @SuppressWarnings("unchecked")
	  public List<Employee> getAll() {
	    return entityManager.createQuery("from Employee").getResultList();
	  }
	  
	  public Employee getById(int id) {
		  return entityManager.find(Employee.class, id);
	  }
	  
	  /**
	   * Save the employee in the database.
	   */
	  public Employee create(Employee emp) {
	    entityManager.persist(emp);
	    int id = emp.getId();
	    return entityManager.find(Employee.class, id);
	  }
	  /**
	   * delete the employee in the database.
	   */
	  public Boolean delete(Employee emp) {
		  if (entityManager.contains(emp))
			  entityManager.remove(emp);
		  else
			  entityManager.remove(entityManager.merge(emp));
		  
		  return true;
	  }
}
