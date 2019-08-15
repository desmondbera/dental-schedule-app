package com.dentalScheduleApp.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.dentalScheduleApp.daoi.DentalOfficeDAOI;
import com.dentalScheduleApp.entities.DentalOffice;

public class DentalOfficeService implements DentalOfficeDAOI  {
	
	@Override
	public List<DentalOffice> getAllDentalOffices() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery("getAllDentalOffices");
		List<DentalOffice> result = query.getResultList();
		entityManager.close();
		entityManagerFactory.close();
		return result;
	}
	
	@Override
	public DentalOffice getOfficeById(Long primaryDentalOfficeId) {
		DentalOffice found = null;
		
		List<DentalOffice> listOfOffices = getAllDentalOffices();
		for(int x = 0; x < listOfOffices.size(); x++) {
			if(listOfOffices.get(x).getId() == primaryDentalOfficeId) {
				found = listOfOffices.get(x);
				break;
			}
		}
		return found;
	}
}
