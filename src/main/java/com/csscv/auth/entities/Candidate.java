package com.csscv.auth.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Candidate {
	@Id
	@GeneratedValue
	private Long Id;
	
	@OneToOne
	private User user;
	
	private String firstName;
	private String middleName;
	private String lastName;
	
	private Byte age;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Address address;
	private String email;
	private String phoneNo;
	
//	same SelectionProcessLink can not be owned by different user
	@OneToMany
	private List<SelectionProcessLink> subscribedSelectionProcessess;
	
	@ManyToMany
	private List<QualificationEntry> qualifications;
	@ManyToMany
	private List<Skill> skills;
	
	
	private boolean active;
	
	
	
	
	
	public List<Skill> getSkills() {
		return skills;
	}
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Byte getAge() {
		return age;
	}
	public void setAge(Byte age) {
		this.age = age;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public List<SelectionProcessLink> getSubscribedSelectionProcessess() {
		return subscribedSelectionProcessess;
	}
	public void setSubscribedSelectionProcessess(List<SelectionProcessLink> subscribedSelectionProcessess) {
		this.subscribedSelectionProcessess = subscribedSelectionProcessess;
	}
	public List<QualificationEntry> getQualifications() {
		return qualifications;
	}
	public void setQualifications(List<QualificationEntry> qualifications) {
		this.qualifications = qualifications;
	}
	
	
	
	
}
