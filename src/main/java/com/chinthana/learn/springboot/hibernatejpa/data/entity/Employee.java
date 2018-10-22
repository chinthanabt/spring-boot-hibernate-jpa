package com.chinthana.learn.springboot.hibernatejpa.data.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * 
 *@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
 * All the sub classes details are kept in single table, Therefore lots-of nullable columns are present in the mapped table
 *
 *@DiscriminatorColumn(name="EmployeeType")
 * We can change DType column name here.
 *
 *@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
 * InheritanceType.TABLE_PER_CLASS will create separate table each concrete class which is implemented from Employee
 * Inherited properties are repeated in sub tables
 *  
 * @Inheritance(strategy=InheritanceType.JOINED)
 * Fields that specific to a subclass are mapped in a separate table
 * No duplications of column
 *
 * @Entity
 * When we used @MappedSuperclass, @Entity is no longer needed
 */
//@Entity When we used @MappedSuperclass, @Entity is no longer needed
@MappedSuperclass
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Employee {
		
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable=false)
	private String name;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	Employee() {
		
	}
	
	public Employee(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}	

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	@Override
	public String toString() {
		return "\n Employee [id=" + id + ", name=" + name + "createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + "]";
	}	
}
