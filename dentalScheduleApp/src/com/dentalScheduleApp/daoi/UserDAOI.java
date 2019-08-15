package com.dentalScheduleApp.daoi;

import java.util.List;

import com.dentalScheduleApp.entities.Appointment;
import com.dentalScheduleApp.entities.DentalOffice;
import com.dentalScheduleApp.entities.Hygienist;
import com.dentalScheduleApp.entities.User;

public interface UserDAOI {

	void scheduleUserAppointment(String userName, String hygienistId);
	void registerUserToHygienist(String userName, String userPassword);
	
	List<Appointment> getUserAppointments(String userName);
	List<Hygienist> getUserFavHygienists(String userName);
	List<Hygienist> getListOfFavoriteHygienistById(Long currentUserId);
	List<User> getAllUsers();

	User getUserByUsername(String userName);
	User getUserById(Long userId);
	
	int getLastItemId();
	DentalOffice getUserPrimOfficeById(Long userId);
	Long getUserIdWithUserNameAndPwd(String username, String password);
	
	boolean validateUser(String userName, String password);
	boolean registerUser(String userName, String address, String firstName, String password, String phoneNumber,
			DentalOffice primaryDental, Hygienist hyg);
	boolean updateUserPrimaryDental(Long currentUserId, Long newDentalOfficeId);
	boolean addToUsersFavHygList(Long currentUserId, Long newHygienistId);
	boolean deleteHygFromListById(Long currentUserId, Long hygienistIdToDelete);
	boolean editUserNameWithId(Long currentUserId, String newUserName);
	boolean editUserAddressWithId(Long currentUserId, String newAddress);
	boolean editFirstNameWithId(Long currentUserId, String newName);
	boolean editUserPhoneNumberWithId(Long currentUserId, String newPhoneNumber);
	boolean checkCurrPassword(Long currentUserId, String oldPassword);
	boolean editUserPwdWithId(Long currentUserId, String newPassword);
	boolean deleteUserAcctById(Long currentUserId);
	boolean updateUser(User u);
	
}
