package com.dentalScheduleApp.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="appointment")
@NamedQueries({
	@NamedQuery(name="queryApptsByUsername", query="SELECT a FROM Appointment a WHERE a.patientName = :aUsername"),
	@NamedQuery(name="queryAppointmentForIdWithUsername", query="SELECT a.id FROM Appointment a WHERE a.patientName = :aUsername"),
	@NamedQuery(name="queryAllAppts", query="SELECT a FROM Appointment a")
})
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="patient_name")
	private String patientName;
	
	@Column(name="hygienist_name")
	private String hygienistName;
	
	@Column(name="date_of_appt")
	@Temporal(TemporalType.DATE)
	private Date dateOfAppt;
	
	@Column(name="time_of_appt")
	@Temporal(TemporalType.TIME)
	private Date timeOfAppt;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "user_appointments")
	private User userAppt;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="appointment_at_office", referencedColumnName = "id")
	private DentalOffice dentalOfficeForAppt;
	
	//Constructors
	public Appointment() {
		super();
	}

	public Appointment(Long id, String patientName, String hygienistName, Date dateOfAppt, Date timeOfAppt,
			User userAppt, DentalOffice dentalOfficeForAppt) {
		super();
		this.id = id;
		this.patientName = patientName;
		this.hygienistName = hygienistName;
		this.dateOfAppt = dateOfAppt;
		this.timeOfAppt = timeOfAppt;
		this.userAppt = userAppt;
		this.dentalOfficeForAppt = dentalOfficeForAppt;
	}

	//Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatientname() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getHygienistName() {
		return hygienistName;
	}

	public void setHygienistName(String hygienistName) {
		this.hygienistName = hygienistName;
	}

	public Date getDate() {
		return dateOfAppt;
	}

	public void setDate(Date dateOfAppt) {
		this.dateOfAppt = dateOfAppt;
	}

	public Date getDateOfAppt() {
		return dateOfAppt;
	}

	public void setDateOfAppt(Date dateOfAppt) {
		this.dateOfAppt = dateOfAppt;
	}

	//Formats date of appointment in a user friendly way.
	public String getDateOfApptFormatted() {
		SimpleDateFormat df = new SimpleDateFormat("E MMM dd, yyyy");
		return df.format(dateOfAppt);
	}
	
	public String getDateOfApptFormattedForEdit() {
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		return df.format(dateOfAppt);
	}
	
	public Date getTimeOfAppt() {
		return timeOfAppt;
	}

	public void setTimeOfAppt(Date timeOfAppt) {
		this.timeOfAppt = timeOfAppt;
	}

	//Formats time of appointment in a user friendly way.
	public String getTimeOfApptFormatted() {
		SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
		return df.format(timeOfAppt);
	}
	
	public User getUserAppt() {
		return userAppt;
	}

	public void setUserAppt(User userAppt) {
		this.userAppt = userAppt;
	}

	public DentalOffice getDentalOfficeForAppt() {
		return dentalOfficeForAppt;
	}

	public void setDentalOfficeForAppt(DentalOffice dentalOfficeForAppt) {
		this.dentalOfficeForAppt = dentalOfficeForAppt;
	}
	
	
}
