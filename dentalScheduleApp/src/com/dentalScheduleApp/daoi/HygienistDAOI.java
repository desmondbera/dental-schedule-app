package com.dentalScheduleApp.daoi;

import java.util.List;

import com.dentalScheduleApp.entities.Hygienist;

public interface HygienistDAOI {

	List<Hygienist> getAllHygienists();

	Hygienist getHygById(Long hygId);

}
