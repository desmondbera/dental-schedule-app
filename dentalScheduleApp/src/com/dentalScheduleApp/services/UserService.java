package com.dentalScheduleApp.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.dentalScheduleApp.daoi.UserDAOI;
import com.dentalScheduleApp.entities.Appointment;
import com.dentalScheduleApp.entities.DentalOffice;
import com.dentalScheduleApp.entities.Hygienist;
import com.dentalScheduleApp.entities.User;



public class UserService implements UserDAOI {
	
	@Override
	public boolean validateUser(String userName, String password) {
		boolean validUser = true;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			Query query = entityManager.createNamedQuery("queryUsernameAndPassword");
			query.setParameter("username", userName);
			query.setParameter("password", password);
			List<User> userList = query.getResultList();
			if(userList.size() == 0) {
				validUser = false;
			}
		} catch(PersistenceException e) {
			e.getMessage();
			validUser = false;
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		
		return validUser;
	}
	@Override 
	public List<Appointment> getUserAppointments(String userName) {
		User user = getUserByUsername(userName);
		if(user != null) {
			System.out.println("User in getuserAppointments is NOT NULL");
			return user.getUserAppts();
		} else {
			System.out.println("User in getUserAppointments is NULL");
			return null;
		}
		
	}
	
	@Override
	public User getUserByUsername(String userName) {
		User found = null;
		List<User> listOfUsers = getAllUsers();
		System.out.println("Our list of users: " + listOfUsers);
		for(int x = 0; x < listOfUsers.size(); x++) {
			System.out.println("listOfUsers at curr index: " + listOfUsers.get(x).getUsername());
			if(listOfUsers.get(x).getUsername().equals(userName)) {
				found = listOfUsers.get(x);
				break;
			}
		}
		return found;
	}
	
	
	@Override
	public List<User> getAllUsers() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager(); 
		
		Query query = entityManager.createNamedQuery("queryAllUsers");
		return query.getResultList();
		
	}

	@Override
	public boolean registerUser(String userName, String address, String firstName, String password, String phoneNumber, DentalOffice primaryDental, List<Hygienist> hygienists) {
		boolean result = true;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//Checking to see if username already exists in DB
		if(getUserByUsername(userName) == null) {
			System.out.println("inside first IF in REGISTERSERVICE...");
			try {
				
				entityManager.getTransaction().begin();
				User user = new User();
//				List<Hygienist> hygList = new ArrayList<>();
//				hygList.add(hyg);
				
				user.setUsername(userName);
				user.setAddress(address);
				user.setName(firstName);
				user.setPassword(password);
				user.setPhoneNumber(phoneNumber);
				user.setPrimaryDentalOffice(primaryDental);
				user.setFavHygienists(hygienists);
				
//				user.getFavHygienists().add(hyg);
//				user.setFavHygienists(hygList);
//				user.setUserAppts(null);
				
				entityManager.persist(user);
				entityManager.getTransaction().commit();
				
			} catch(PersistenceException e) {
				result = false;
				e.printStackTrace();
			} finally {
				entityManager.close();
				entityManagerFactory.close();
			}
		} else {
			result = false;
		}
		return result;
		
	}
	
	@Override
	public User getUserById(Long userId) {
		User foundUser = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			foundUser = entityManager.find(User.class, userId);
			System.out.println("Found user is: " + foundUser.getUsername());
		} catch (PersistenceException e) {
			e.printStackTrace();
			
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		return foundUser;
	}
	
	
	@Override
	public DentalOffice getUserPrimOfficeById(Long userId) {
		DentalOffice result = null;
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			User user = entityManager.find(User.class, userId);
			DentalOffice dentalOffice = entityManager.find(DentalOffice.class, user.getPrimaryDentalOffice().getId());
			result = dentalOffice;
			
		} catch(PersistenceException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		
		return result;
		
	}
	
	@Override
	public Long getUserIdWithUserNameAndPwd(String username, String password) {
		Long result = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			Query query = entityManager.createNamedQuery("queryUserForIdWithUsernameAndPwd");
			query.setParameter("username", username);
			query.setParameter("password", password);
			System.out.println("Result in getUserIdWithUserNameAndPwd is: " + query.getResultList().get(0));
			result = (Long) query.getResultList().get(0);
			
			
		} catch(PersistenceException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		return result;
	}
	
	@Override
	public boolean updateUserPrimaryDental(Long currentUserId, Long newDentalOfficeId) {
		boolean result = true;
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			User user = entityManager.find(User.class, currentUserId);
			user.setPrimaryDentalOffice(entityManager.find(DentalOffice.class, newDentalOfficeId));
			entityManager.getTransaction().commit();
		} catch(PersistenceException e) {
			e.printStackTrace();
			result = false;
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		
		return result;
	}
	@Override
	public List<Hygienist> getListOfFavoriteHygienistById(Long currentUserId) {
		List<Hygienist> listOfHyg = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			User user = entityManager.find(User.class, currentUserId);
			listOfHyg = user.getFavHygienists();
		} catch(PersistenceException e) {
			e.printStackTrace();
			
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		return listOfHyg;
		
	}
	
	@Override
	public boolean addToUsersFavHygList(Long currentUserId, Long newHygienistId) {
		boolean result = true;
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			User user = entityManager.find(User.class, currentUserId);
			Hygienist hyg = entityManager.find(Hygienist.class, newHygienistId);
			user.getFavHygienists().add(hyg);
			entityManager.getTransaction().commit();
		} catch(PersistenceException e) {
			e.printStackTrace();
			result = false;
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		
		return result;
	}
	@Override
	public boolean deleteHygFromListById(Long currentUserId, Long hygienistIdToDelete) {
		boolean result = true;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			User user = entityManager.find(User.class, currentUserId);
			List<Hygienist> listOfUsersFavHyg = user.getFavHygienists();
			for(int x = 0; x < listOfUsersFavHyg.size(); x++) {
				if(listOfUsersFavHyg.get(x).getId().equals(hygienistIdToDelete)) {
					listOfUsersFavHyg.remove(x);
					break;
				}
			}
			
			entityManager.getTransaction().commit();
		} catch(PersistenceException e) {
			e.printStackTrace();
			result = false;
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		return result;
	}
	
	@Override
	public boolean editUserNameWithId(Long currentUserId, String newUserName) {
		boolean result = true;
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			User user = entityManager.find(User.class, currentUserId);
			user.setUsername(newUserName);			
			entityManager.getTransaction().commit();
		} catch(PersistenceException e) {
			e.printStackTrace();
			result = false;
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		return result;
	}
	
	@Override
	public boolean editUserAddressWithId(Long currentUserId, String newAddress) {
		boolean result = true;
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			User user = entityManager.find(User.class, currentUserId);
			user.setAddress(newAddress);		
			entityManager.getTransaction().commit();
		} catch(PersistenceException e) {
			e.printStackTrace();
			result = false;
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		
		return result;
	}
	
	@Override
	public boolean editFirstNameWithId(Long currentUserId, String newName) {
		boolean result = true;
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			User user = entityManager.find(User.class, currentUserId);
			user.setName(newName);		
			entityManager.getTransaction().commit();
		} catch(PersistenceException e) {
			e.printStackTrace();
			result = false;
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		
		
		return result;
	}
	
	@Override
	public boolean editUserPhoneNumberWithId(Long currentUserId, String newPhoneNumber) {
		boolean result = true;
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			User user = entityManager.find(User.class, currentUserId);
			user.setPhoneNumber(newPhoneNumber);
			entityManager.getTransaction().commit();
		} catch(PersistenceException e) {
			e.printStackTrace();
			result = false;
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		
		return result;
	}
	
	@Override
	public boolean checkCurrPassword(Long currentUserId, String oldPassword) {
		boolean match = true;
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			User user = entityManager.find(User.class, currentUserId);
			if(!user.getPassword().contentEquals(oldPassword)) {
				match = false;
			}
		} catch(PersistenceException e) {
			e.printStackTrace();
			match = false;
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}

		return match;
	}
	
	@Override
	public boolean editUserPwdWithId(Long currentUserId, String newPassword) {
		boolean result = true;
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			User user = entityManager.find(User.class, currentUserId);
			user.setPassword(newPassword);
			entityManager.getTransaction().commit();
		} catch(PersistenceException e) {
			e.printStackTrace();
			result = false;
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		
		return result;
	}
	
	@Override
	public boolean deleteUserAcctById(Long currentUserId) {
		boolean result = true;
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			User user = entityManager.find(User.class, currentUserId);
			entityManager.remove(user);
			entityManager.getTransaction().commit();
		} catch(PersistenceException e) {
			e.printStackTrace();
			result = false;
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		
		
		return result;
	}
	
	@Override
	public boolean updateUser(User u) {
		boolean result = true;		
		EntityManagerFactory enMaFact = Persistence.createEntityManagerFactory("dentalScheduleApp");
		EntityManager enManager = enMaFact.createEntityManager();
		try {
			enManager.getTransaction().begin();
			System.out.println("Our u.getId is: " + u.getId());
			System.out.println("Our u.getUsername is: " + u.getUsername());
			System.out.println("Our u.getName is: " + u.getName());
			User foundUser = enManager.find(User.class, u.getId());
			foundUser.setUsername(u.getUsername());
//			foundEmp.setFirstName(emp.getFirstName());
//			foundEmp.setLastName(emp.getLastName());
//			foundEmp.setEmail(emp.getEmail());
			enManager.getTransaction().commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			result = false;
		} finally {
			enManager.close();
			enMaFact.close();
		}
		
		return result;
	}
	
	
}
