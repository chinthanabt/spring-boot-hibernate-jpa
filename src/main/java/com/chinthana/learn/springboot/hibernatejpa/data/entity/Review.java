package com.chinthana.learn.springboot.hibernatejpa.data.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name="review")
@NamedQueries(value={@NamedQuery(name="Find_All_Reviews", query="select r from Review r"), @NamedQuery(name="Find_Review_By_description", query="select r from Review r where description like '%chin%' ")})
public class Review {
		
	@Id
	@GeneratedValue
	private int id;
	
	private String description;
	
	private String rating;
	
	//The relationship annotation we are putting in owner side of the relation, here we considering the Review as the owner and course id will be foreign key in Review table.
	//The map by will be put in the non owner (Course) side.
	@ManyToOne
	private Course course;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	Review() {
		
	}
	
	public Review(String rating, String description) {
		super();
		this.rating = rating;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}	

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	@Override
	public String toString() {
		return "\n Review [id=" + id + ", description=" + description + ", rating=" + rating + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + "]";
	}
	
}
