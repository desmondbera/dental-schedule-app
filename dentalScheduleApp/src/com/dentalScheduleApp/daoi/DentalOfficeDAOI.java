package com.dentalScheduleApp.daoi;

import java.util.List;

import com.dentalScheduleApp.entities.DentalOffice;


public interface DentalOfficeDAOI {

	List<DentalOffice> getAllDentalOffices();
	DentalOffice getOfficeById(Long primaryDentalOfficeId);

}
