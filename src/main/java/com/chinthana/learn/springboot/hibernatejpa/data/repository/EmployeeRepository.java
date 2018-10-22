package com.chinthana.learn.springboot.hibernatejpa.data.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chinthana.learn.springboot.hibernatejpa.data.entity.Employee;
import com.chinthana.learn.springboot.hibernatejpa.data.entity.FullTimeEmployee;
import com.chinthana.learn.springboot.hibernatejpa.data.entity.PartTimeEmployee;

@Repository
@Transactional
public class EmployeeRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PersistenceContext
	EntityManager entityManager;
	
	//insert employee
	public void insert(Employee employee) {		
		entityManager.persist(employee);
	}
	
	//Find all employees	
	//Employee is no longer entity since we are using @MappedSuperclass in Employee class
//	public List<Employee> getAll() {		
//		return entityManager.createQuery("select e from Employee e", Employee.class).getResultList();
//	}
	
	//Find Full time employees
	public List<FullTimeEmployee> getAllFullTime() {		
		return entityManager.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
	}
	
	//Find Part time employees
	public List<PartTimeEmployee> getAllPartTime() {		
		return entityManager.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
	}
	
}
