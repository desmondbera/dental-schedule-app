package com.dentalScheduleApp.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@Entity
@Table(name="user")
@NamedQueries({
	@NamedQuery(query = "SELECT u FROM User u", name="queryAllUsers"),
	@NamedQuery(query = "SELECT u.username, u.password FROM User u WHERE u.username = :username AND u.password = :password", name="queryUsernameAndPassword"),
	@NamedQuery(query = "SELECT u.id FROM User u WHERE u.username = :username AND u.password = :password ", name = "queryUserForIdWithUsernameAndPwd")
})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 20, message = "Username length must be between {2} and {1}")
	@Column(name="username")
	private String username;
	
	@NotNull
	@Size(min = 2, max = 20, message = "Your name length must be between {2} and {1}")
	@Column(name="first_name")
	private String name;
	
	@NotNull
	@Size(min = 4, max = 16, message = "Your password length must be between {2} and {1}")
	@Column(name="password")
	private String password;
	
	@NotNull
	@Size(min = 4, max = 16, message = "Your confirm password length must be between {2} and {1}")
	@Transient
	private String confirmPassword;
	
	@NotNull
	@Size(min = 4, max = 16, message = "Your address length must be between {2} and {1}")
	@Column(name="address")
	private String address;
	
	@NotNull
	@Size(min = 4, max = 16, message = "Your phone number must be between {2} and {1}")
	@Column(name="phone_number")
	private String phoneNumber;
	
	@NotNull
	@OneToOne
	private DentalOffice primaryDentalOffice;

	@NotNull
	@ManyToMany
	@JoinTable(name = "user_hygienists", 
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
		inverseJoinColumns=@JoinColumn(name = "hygienist_id", referencedColumnName = "id"))
	private List<Hygienist> favHygienists;
	
	//TODO: Above is good to go. Need to update everything below...
	@OneToMany(mappedBy="userAppt")
	private List<Appointment> userAppts;
	
	//Constructors
	public User() {
		super();
	}

	public User(Long id, String username, String name, String password, String address, String phoneNumber,
			DentalOffice primaryDentalOffice, List<Hygienist> favHygienists, List<Appointment> userAppts) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.primaryDentalOffice = primaryDentalOffice;
		this.favHygienists = favHygienists;
		this.userAppts = userAppts;
//		this.dentalOfficeUsers = dentalOfficeUsers;
	}

	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Hygienist> getFavHygienists() {
		return favHygienists;
	}

	public void setFavHygienists(List<Hygienist> favHygienists) {
		this.favHygienists = favHygienists;
	}

	public List<Appointment> getUserAppts() {
		return userAppts;
	}

	public void setUserAppts(List<Appointment> userAppts) {
		this.userAppts = userAppts;
	}

	public DentalOffice getPrimaryDentalOffice() {
		return primaryDentalOffice;
	}

	public void setPrimaryDentalOffice(DentalOffice primaryDentalOffice) {
		this.primaryDentalOffice = primaryDentalOffice;
	}

	// This will add it on both sides; to User entity AND hygienist side.
	public void addHygienist(Hygienist hygienist) {
		this.favHygienists.add(hygienist);
		hygienist.getUser().add(this);
		
	}

	public void removeHygienist(Hygienist hygienist) {
		this.favHygienists.remove(hygienist);
		hygienist.getUser().remove(this);
	}
	
	
	
}
