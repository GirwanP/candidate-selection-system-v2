package com.csscv.auth.dto;


public class RecruiterDto {
	private Long Id;
	
//	private User user;
	
	private String username;
	
	private String companyName;
	private String password;
	
	private String panNo;
	
//	private Address address;
	private String email;
	private String phoneNo;
	private String passwordConfirm;
	
//	same SelectionProcessLink can not be owned by different user
//	@ManyToMany
//	private List<QualificationEntry> qualifications;
	private String country;
	private String state;
	private String municipalty;
	
	private boolean active;
	
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMunicipalty() {
		return municipalty;
	}
	public void setMunicipalty(String municipalty) {
		this.municipalty = municipalty;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
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

	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
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
