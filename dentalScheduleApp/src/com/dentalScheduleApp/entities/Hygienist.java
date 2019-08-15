package com.dentalScheduleApp.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="hygienist")
public class Hygienist {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="hygienist_name")
	private String name;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="list_of_fav_hygienist")
//	private User userHygienist;
	
//	@ManyToMany(cascade = CascadeType.REMOVE)
//	@JoinTable(name = "User_Hygienists", 
//	joinColumns = 
//		{ @JoinColumn(name = "user_id", referencedColumnName = "id")}, 
//	inverseJoinColumns = 
//		{
//			@JoinColumn(name="hygienist_id", referencedColumnName = "id")
//	})
//	private List<User> user;

	@ManyToMany(mappedBy="favHygienists") List<User> user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="list_of_offices")
	private DentalOffice dentalOfficeHygienists;
	
	//Constructors
	public Hygienist() {
		super();
	}
	
	public Hygienist(String name, DentalOffice dentalOfficeHygienists) {
		super();
		this.name = name;
//		this.userHygienist = userHygienist;
		this.dentalOfficeHygienists = dentalOfficeHygienists;
	}

	//Getters and setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public User getUserHygienist() {
//		return userHygienist;
//	}

//	public void setUserHygienist(User userHygienist) {
//		this.userHygienist = userHygienist;
//	}

	public DentalOffice getDentalOffice() {
		return dentalOfficeHygienists;
	}

	public void setDentalOffice(DentalOffice dentalOffice) {
		this.dentalOfficeHygienists = dentalOffice;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}	
	
	
	
}
