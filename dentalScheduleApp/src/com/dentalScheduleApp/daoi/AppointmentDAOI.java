package com.dentalScheduleApp.daoi;


import java.util.Date;
import java.util.List;

import com.dentalScheduleApp.entities.Appointment;

public interface AppointmentDAOI {

	List<Appointment> getListOfApptsByUsername(String name);
	List<Appointment> getListOfApptsById(Long id);
	List<Appointment> getAllAppointments();
	
	List<Long> getListOfAppointmentIdsWithUsername(String username);
	
	boolean addAppointment(Date dateOfAppt, String hygienistName, String patientName, Date timeOfAppt);
	boolean deleteAppointmentById(Long apptId);
	boolean updateAppointmentDateById(Long apptId, Date updatedDateObj);
	
	Appointment getAppointmentById(Long apptId);
	boolean deleteAllUserAppointments(List<Long> listOfAllApptIds);
	boolean updateApptUsernameById(Long apptId, String updatedUsername);
	
}
