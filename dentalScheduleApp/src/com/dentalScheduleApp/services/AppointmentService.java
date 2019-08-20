package com.dentalScheduleApp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.dentalScheduleApp.daoi.AppointmentDAOI;
import com.dentalScheduleApp.entities.Appointment;
import com.dentalScheduleApp.entities.DentalOffice;
import com.dentalScheduleApp.entities.User;

public class AppointmentService implements AppointmentDAOI {

	@Override
	public List<Appointment> getListOfApptsByUsername(String userName) {

		List<Appointment> listOfUsersAppts = new ArrayList<>();

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			Query query = entityManager.createNamedQuery("queryApptsByUsername");
			query.setParameter("aUsername", userName);
			listOfUsersAppts = query.getResultList();

		} catch (PersistenceException e) {
			e.getMessage();
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		return listOfUsersAppts;
	}

	@Override
	public List<Appointment> getListOfApptsById(Long id) {
		List<Appointment> listOfUsersAppts = new ArrayList<>();

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			// TODO: make the named query in APPOINTMENT ENTITY
			Query query = entityManager.createNamedQuery("queryApptsById");
			query.setParameter("aId", id);
			listOfUsersAppts = query.getResultList();

		} catch (PersistenceException e) {
			e.getMessage();
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		return listOfUsersAppts;
	}

	@Override
	public boolean addAppointment(Date dateObj, String hygienistName, String patientName, Date dateTimeObj) {
		System.out.println("--inside addAppointment!--");
		boolean result = true;

		System.out.println("Our dateObj is: " + dateObj);
		System.out.println("Our hygienistName is: " + hygienistName);
		System.out.println("Our patientName is: " + patientName);
		System.out.println("Our dateTimeObj is: " + dateTimeObj);

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			System.out.println("Inside try...");
			entityManager.getTransaction().begin();

			Appointment appt = new Appointment();
			appt.setDate(dateObj);
			appt.setHygienistName(hygienistName);
			appt.setPatientName(patientName);
			appt.setTimeOfAppt(dateTimeObj);

			entityManager.persist(appt);
			entityManager.getTransaction().commit();
		} catch (PersistenceException e) {
			System.out.println("Inside catch...");
			e.printStackTrace();
			result = false;
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}

		System.out.println("Our result is: " + result);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getListOfAppointmentIdsWithUsername(String username) {
		List<Long> listOfUserAllAppts = new ArrayList<>();
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			Query query = entityManager.createNamedQuery("queryAppointmentForIdWithUsername");
			query.setParameter("aUsername", username);
			System.out.println("result: " + query.getResultList());
			listOfUserAllAppts = query.getResultList();

		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}

		return listOfUserAllAppts;
	}

	@Override
	public boolean deleteAppointmentById(Long apptId) {
		boolean result = true;

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			Appointment appt = entityManager.find(Appointment.class, apptId);
			entityManager.remove(appt);
			entityManager.getTransaction().commit();
		} catch (PersistenceException e) {
			result = false;
			e.printStackTrace();
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}

		return result;
	}

	@Override
	public boolean updateAppointmentDateById(Long apptId, Date updatedDateObj) {
		boolean result = true;

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			Appointment appt = entityManager.find(Appointment.class, apptId);
			appt.setDate(updatedDateObj);
			entityManager.getTransaction().commit();
		} catch (PersistenceException e) {
			result = false;
			e.printStackTrace();
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		
		return result;
	}

	@Override
	public List<Appointment> getAllAppointments() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery("queryAllAppts");
		return query.getResultList();
	}

	@Override
	public Appointment getAppointmentById(Long apptId) {
		Appointment found = null;

		List<Appointment> listOfAppts = getAllAppointments();
//		System.out.println("Our list of appointments: " + listOfAppts);
		for (int x = 0; x < listOfAppts.size(); x++) {
			System.out.println("listOfAppts at curr index: " + listOfAppts.get(x).getId());
			if (listOfAppts.get(x).getId().equals(apptId)) {
				found = listOfAppts.get(x);
				break;
			}
		}
		return found;

	}
	
	@Override
	public boolean deleteAllUserAppointments(List<Long> listOfAllApptIds) {
		boolean result = true;
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			for(int x = 0; x < listOfAllApptIds.size(); x++) {
				Appointment appt = entityManager.find(Appointment.class, listOfAllApptIds.get(x));
				entityManager.remove(appt);
			}
				
			entityManager.getTransaction().commit();
		} catch (PersistenceException e) {
			result = false;
			e.printStackTrace();
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		
		return result;
	}
	
	@Override 
	public boolean updateApptUsernameById(Long apptId, String updatedUsername) {
		boolean result = true;
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			System.out.println("====Inside try of UPDATEAPPTUSERNAMEBYID===");
			entityManager.getTransaction().begin();
			Appointment appt = entityManager.find(Appointment.class, apptId);
			System.out.println("Apt.getPatientName() before is: " + appt.getPatientname());
			appt.setPatientName(updatedUsername);
			System.out.println("Apt.getPatientName() after is: " + appt.getPatientname());
			entityManager.getTransaction().commit();
		} catch (PersistenceException e) {
			result = false;
			e.printStackTrace();
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		System.out.println("Our result inside updateApptUsernameByid....: " + result);
		return result;
	}

}
