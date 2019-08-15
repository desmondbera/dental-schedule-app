package com.dentalScheduleApp.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.dentalScheduleApp.daoi.HygienistDAOI;
import com.dentalScheduleApp.entities.Hygienist;

public class HygienistService implements HygienistDAOI {
	
	@Override
	public List<Hygienist> getAllHygienists() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("SELECT h FROM Hygienist h");
		List<Hygienist> result = query.getResultList();
		entityManager.close();
		entityManagerFactory.close();
		return result;
	}
	
	@Override
	public Hygienist getHygById(Long hygId) {
		Hygienist result = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			Hygienist hyg = entityManager.find(Hygienist.class, hygId);
			result = hyg;
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		return result;
	}
}
