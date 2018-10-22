package com.chinthana.learn.springboot.hibernatejpa.data.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chinthana.learn.springboot.hibernatejpa.data.entity.Review;

@Repository
@Transactional
public class ReviewRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public Review findById(int id) {
		return entityManager.find(Review.class, id);
	}
	
}
