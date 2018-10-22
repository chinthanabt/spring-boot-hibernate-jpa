package com.chinthana.learn.springboot.hibernatejpa.data.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name="student")
@NamedQueries(value={@NamedQuery(name="Find_All_Students", query="select s from Student s"), @NamedQuery(name="Find_Stident_By_Name", query="select s from Student s where name like '%chin%' ")})
public class Student {
		
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	//The relationship annotation we are putting in owner side of the relation, here the course is own by the student.
	//The map by will be put in the non owner side.
	@OneToOne
	private Passport passport;
	
	@ManyToMany
	@JoinTable(name="STUDENT_COURSE", joinColumns=@JoinColumn(name="STUDENT_ID"), inverseJoinColumns= @JoinColumn(name="COURSE_ID"))
	private List<Course> courses = new ArrayList<>();	
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	Student() {		
	}
	
	public Student(String name) {
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

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}
	
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	public void setCourse(Course course) {
		this.courses.add(course);
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	@Override
	public String toString() {
		return "\n Student [id=" + id + ", name=" + name + "]";
	}
	
}
