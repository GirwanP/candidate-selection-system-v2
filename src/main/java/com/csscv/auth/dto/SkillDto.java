package com.csscv.auth.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

public class SkillDto {

	private Long Id;
	
	private String skillName;
	
	
	private Long skillLevelId;
	
	private boolean valid;
	
	
	
	
	public Long getSkillLevelId() {
		return skillLevelId;
	}
	public void setSkillLevelId(Long skillLevelId) {
		this.skillLevelId = skillLevelId;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	
	
	
	
	
}
