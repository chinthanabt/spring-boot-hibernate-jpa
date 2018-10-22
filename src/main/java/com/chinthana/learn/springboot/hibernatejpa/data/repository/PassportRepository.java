package com.chinthana.learn.springboot.hibernatejpa.data.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chinthana.learn.springboot.hibernatejpa.data.entity.Passport;

@Repository
@Transactional
public class PassportRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	//Find by id using JPA
	public Passport findById(int id) {		
		return entityManager.find(Passport.class, id);
	}
	
	//Typed query using JPQL
	public List<Passport> findByNameTypedQuery() {
		TypedQuery<Passport> typedQurary = entityManager.createQuery("select c from Passport c where name like '%boot%'", Passport.class);
		return typedQurary.getResultList();
	}
	
	//Named query using JPQL
	public List<Passport> findAll() {
		TypedQuery<Passport> namedQueary = entityManager.createNamedQuery("Find_All_Passports", Passport.class);
		return namedQueary.getResultList();
	}
	
	//Named query using JPQL
	public List<Passport> findByName() {
		TypedQuery<Passport> namedQueary = entityManager.createNamedQuery("Find_Passports_By_Name", Passport.class);
		return namedQueary.getResultList();
	}
	
	//Native Query
	public List<Passport> findByName_native_query() {
		Query nativeQueary = entityManager.createNativeQuery("select * from passport", Passport.class);
		List<Passport> list = nativeQueary.getResultList();
		return list;
	}
	
	//Native Query with named parameter
	public List<Passport> findByName_native_query_with_named_parameter() {
		Query nativeQueary = entityManager.createNativeQuery("select * from passport where id =:id", Passport.class);
		nativeQueary.setParameter("id", 10001);
		List<Passport> list = nativeQueary.getResultList();
		return list;
	}
	
	// Native Query with named parameter
	//We can have to native queries when doing the mass updates.
	public int mass_update() {
		Query nativeQueary = entityManager.createNativeQuery("update COURSE_DETAILS set updated_date = sysdate", Passport.class);	
		return nativeQueary.executeUpdate();
	}	
	
	//Update using JPA Entity manager
	public Passport update(Passport student) {		
		return entityManager.merge(student);
	}	
	
	//Update/Save using JPA Entity manager
	public void save(Passport student) {		
		if(findById(student.getId()) == null)
			entityManager.persist(student);
		else 
			entityManager.merge(student);		
	}
	
	//Delete using JPA Entity manager
	public void deleteById(int id) {
		entityManager.remove(findById(id));
	}
	
	public void playWithEntityManager() {
		Passport passport = new Passport("Web Services");
		entityManager.persist(passport);		
		entityManager.flush();		
		
		Passport passport1 = new Passport("Angular JS");
		entityManager.detach(passport1);		
		
		passport1.setName("Angular JS - Updated");
		passport1.setName("Web Services - Updated");		
	}
	
	//If an Exception occurred while in a transaction even flushed data also be rolled back 
	public void updatePassport() {		
		Passport passport = entityManager.find(Passport.class, 40001);
		passport.setName("E12234234-Updated");
		entityManager.persist(passport);
		entityManager.flush();
		
		passport.setName("E12234234-Updated-Upated");
		entityManager.persist(passport);
		entityManager.flush();
		
		passport.setName("E12234234-Updated-Upated");
		entityManager.persist(passport);
		if(1/0 == 1) {
			
		}
	}
}
