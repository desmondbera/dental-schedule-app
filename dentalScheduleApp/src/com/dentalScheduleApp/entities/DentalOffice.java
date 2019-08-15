package com.dentalScheduleApp.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="dental_office")
@NamedQueries({
	@NamedQuery(name="getAllDentalOffices", query="SELECT d FROM DentalOffice d")
})
public class DentalOffice {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="office_name")
	private String officeName;
	
	@Column(name="office_phone_number")
	private String officePhoneNumber;
	
	@Column(name="office_address")
	private String officeAddress;
	
	@OneToOne(mappedBy = "primaryDentalOffice", cascade = CascadeType.ALL)
	private User user;
	
//	@OneToMany(mappedBy="dentalOfficeHygienists")
//	private List<Hygienist> allHygienstsAtOffice;

//	@OneToMany(mappedBy="dentalOfficeUsers")
//	private List<User> allUsersAtOffice;
	
//	@OneToMany(mappedBy="dentalOfficeForAppt")
//	private List<Appointment> listOfAppts;
//	
	//Constructors
	public DentalOffice() {
		super();
	}

	public DentalOffice(Long id, String officeName, String officePhoneNumber, String officeAddress, User user) {
		super();
		this.id = id;
		this.officeName = officeName;
		this.officePhoneNumber = officePhoneNumber;
		this.officeAddress = officeAddress;
		this.user = user;
//		this.allHygienstsAtOffice = allHygienstsAtOffice;
//		this.listOfAppts = listOfAppts;
	}

	//Getters and Setters
	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getOfficePhoneNumber() {
		return officePhoneNumber;
	}

	public void setOfficePhoneNumber(String officePhoneNumber) {
		this.officePhoneNumber = officePhoneNumber;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	public List<Hygienist> getAllHygienstsAtOffice() {
//		return allHygienstsAtOffice;
//	}
//
//	public void setAllHygienstsAtOffice(List<Hygienist> allHygienstsAtOffice) {
//		this.allHygienstsAtOffice = allHygienstsAtOffice;
//	}
	
	
}
