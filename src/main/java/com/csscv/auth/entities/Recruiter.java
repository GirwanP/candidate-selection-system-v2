package com.csscv.auth.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Recruiter {
	@Id
	@GeneratedValue
	private Long Id;
	
	@OneToOne
	private User user;
	
	private String companyName;
	
	private String panNo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Address address;
	private String email;
	private String phoneNo;
	
//	same SelectionProcessLink can not be owned by different user
	@OneToMany
	private List<SelectionProcess> companysJobListings;
	
//	@ManyToMany
//	private List<QualificationEntry> qualifications;
	
	
	private boolean active;
	private boolean deleted;
	
	
	
	
	public boolean isDelete() {
		return deleted;
	}
	public void setDelete(boolean deleted) {
		this.deleted = deleted;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public List<SelectionProcess> getCompanysJobListings() {
		return companysJobListings;
	}
	public void setCompanysJobListings(List<SelectionProcess> companysJobListings) {
		this.companysJobListings = companysJobListings;
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
	
	
	
	
	
}
